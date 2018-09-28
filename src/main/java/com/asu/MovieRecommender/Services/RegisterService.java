package com.asu.MovieRecommender.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asu.MovieRecommender.Constants.MovieRecommenderConstants;
import com.asu.MovieRecommender.DBServices.UserRepoCrud;
import com.asu.MovieRecommender.Exceptions.RegisterException;
import com.asu.MovieRecommender.User.User;

/**
 * 
 * @author kumar
 * This serves as a register service which handles encoding, adding and editing user.
 */
@Service
public class RegisterService {

	@Autowired
	private UserRepoCrud userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
/**
 * 
 * @param userName
 * @return
 * @throws RegisterException
 * This method checks if the user exists in the repository using the UserName.
 */
	public boolean ifUserExists(String userName) throws RegisterException {

		return (null != userRepo.findByUserName(userName));

	}
/**
 * 
 * @param userDefine
 * @return
 * @throws RegisterException
 * This method adds the user to the repository after password encoding.
 */
	public boolean addUser(User userDefine) throws RegisterException {
		try {
			userDefine.setUserPassword(passwordEncoder.encode(userDefine.getUserPassword()));
			userRepo.insert(userDefine);
			return true;
		} catch (Exception ex) {
			throw new RegisterException(ex.getMessage());
		}

	}
/**
 * 
 * @param userDefine
 * @return
 * @throws RegisterException
 * 
 * This method is for editing the User details.
 * 
 */
	public boolean editUser(User userDefine) throws RegisterException {

		try {
			userDefine.setId(userRepo.findByUserName(userDefine.getUserName()).getId());
			userRepo.save(userDefine);
			return true;
		} catch (Exception ex) {
			throw new RegisterException(ex.getMessage());
		}

	}

	/**
	 * 
	 * @param strContactNo
	 * @return
	 * This method returns if any user exists with a given contactNo.
	 */
	public boolean ifContactNoExists(String strContactNo) {
		return (null != userRepo.findByUserContactNo(strContactNo));

	}

	/**
	 * 
	 * @param strEmailId
	 * @return
	 * This method checks if there is a registered user with the given email ID.
	 */
	public boolean ifEmailIdExists(String strEmailId) {
		return (null != userRepo.findByUserEmailId(strEmailId));

	}
	
	/**
	 * 
	 * @param userDefine
	 * @param operationType
	 * @return
	 * This method does the proper validation and adds the user assigning proper HTTP code to the response.
	 */
	public ResponseEntity<String> addUserAfterValidation(User userDefine, String operationType) {
		String strUserName = userDefine.getUserName();
		String strEmailId = userDefine.getUserEmailId();
		String strContactNo = userDefine.getUserContactNo();
		ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
		if (!strUserName.isEmpty() && !strEmailId.isEmpty() && !strContactNo.isEmpty()) {
			try {
				if (!ifUserExists(strUserName)
						&& operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_NEW_USER)) {
					if (!ifContactNoExists(strContactNo)) {
						if (!ifEmailIdExists(strEmailId)) {
							if (operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_NEW_USER)) {
								if (addUser(userDefine)) {
									return new ResponseEntity<>(HttpStatus.OK);
								}
							}
						}
					} else {
						return new ResponseEntity<>("User with the same Contact No already exists", HttpStatus.OK);

					}
				} else {
					if (operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_EDIT_USER)
							&& ifUserExists(strUserName)) {
						if (editUser(userDefine)) {

							return new ResponseEntity<>("Success", HttpStatus.OK);

						}
					}

					return new ResponseEntity<>("User Exists with same userName", HttpStatus.CONFLICT);

				}
			} catch (RegisterException e) {
				return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

			}

		} else {
			return new ResponseEntity<>("No Username found", HttpStatus.BAD_REQUEST);

		}
		return response;
	}

}
