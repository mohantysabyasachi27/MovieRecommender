package com.asu.MovieRecommender.Services;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asu.MovieRecommender.Constants.MovieRecommenderConstants;
import com.asu.MovieRecommender.DBServices.UserRepoCrud;
import com.asu.MovieRecommender.Exceptions.RegisterException;
import com.asu.MovieRecommender.User.Response;
import com.asu.MovieRecommender.User.User;

@Service
public class RegisterService {
	public static Logger logger = LogManager.getLogger(RegisterService.class);

	@Autowired
	private UserRepoCrud userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean ifUserExists(String userName) throws RegisterException {
		return (null != userRepo.findByUserName(userName));

	}

	public boolean addUser(User userDefine) throws RegisterException {
		try {
			userDefine.setUserPassword(passwordEncoder.encode(userDefine.getUserPassword()));
			userRepo.insert(userDefine);
			return true;
		} catch (Exception ex) {
			throw new RegisterException(ex.getMessage());
		}

	}

	public boolean editUser(User userDefine) throws RegisterException {

		try {
			User oldEntry =userRepo.findByUserName(userDefine.getUserName());
			userDefine.setId(oldEntry.getId());
			userDefine.setUserPassword(oldEntry.getUserPassword());
			userRepo.save(userDefine);
			return true;
		} catch (Exception ex) {
			throw new RegisterException(ex.getMessage());
		}

	}

	public boolean ifContactNoExists(String strContactNo) {
		return (null != userRepo.findByUserContactNo(strContactNo));

	}

	public boolean ifEmailIdExists(String strEmailId) {
		return (null != userRepo.findByUserEmailId(strEmailId));

	}

	private boolean validateUserRequest(Response response, User user, String operationType) {

		if (StringUtils.isBlank(user.getUserName())) {
			response.setStatusCode("100");
			response.setSuccess(false);
			response.setErrorReason("UserName is null or blank");
			logger.info("UserName is null or blank");
			return false;
		}

		if (StringUtils.isBlank(user.getUserPassword())
				& operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_NEW_USER)) {
			response.setStatusCode("101");
			response.setSuccess(false);
			response.setErrorReason("Password is null or blank");
			logger.info("Password is null or blank");
			return false;
		}

		if (ifContactNoExists(user.getUserContactNo())
				&& operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_NEW_USER)) {
			response.setStatusCode("102");
			response.setSuccess(false);
			response.setErrorReason("Contact Number already exists !");
			logger.info("Contact Number already exists !");
			return false;
		}

		if (ifEmailIdExists(user.getUserEmailId())
				&& operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_NEW_USER)) {
			response.setStatusCode("103");
			response.setSuccess(false);
			response.setErrorReason("Email Id is already registered !");
			logger.info("Email Id is already registered !");
			return false;
		}

		try {
			if (ifUserExists(user.getUserName())
					&& operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_NEW_USER)) {
				response.setStatusCode("104");
				response.setSuccess(false);
				response.setErrorReason("UserName is already registered !");
				logger.info("UserName is already registered !");
				return false;
			}
		} catch (RegisterException e) {
			logger.error("Error while checking if the user Exists in the database ", e);
			response.setStatusCode("500");
			response.setSuccess(false);
			response.setErrorReason("Internal Server Error!");
			return false;
		}

		return true;
	}

	public ResponseEntity<Response> addUser(User user, String operationType) {
		Response response = new Response();

		if (!validateUserRequest(response, user, operationType)) {
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		try {

			if (operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_NEW_USER) && addUser(user)) {
				return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), true, ""), HttpStatus.OK);
			} else if (operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_EDIT_USER) && editUser(user)) {
				return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, ""), HttpStatus.OK);
			}
		} catch (RegisterException e) {
			logger.error("Exception while checking User Exists in the database ", e);
			response.setStatusCode("500");
			response.setSuccess(false);
			response.setErrorReason("Internal Server Error!");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		
		logger.info("Invalid Operation Type ");
		response.setStatusCode("400");
		response.setSuccess(false);
		response.setErrorReason("Bad Request!");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/*public ResponseEntity<Response> addUserAfterValidation(User userDefine, String operationType) {

		String strUserName = userDefine.getUserName();
		String strEmailId = userDefine.getUserEmailId();
		String strContactNo = userDefine.getUserContactNo();
		//ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
		if (!StringUtils.isBlank(strUserName) 
				&& !StringUtils.isBlank(strContactNo) && !StringUtils.isBlank(strEmailId)) {
			try {
				if (!ifUserExists(strUserName)
						&& operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_NEW_USER)) {
					logger.info("Adding New User");
					if (!ifContactNoExists(strContactNo)) {
						if (!ifEmailIdExists(strEmailId)) {
							if (operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_NEW_USER)) {
								if (addUser(userDefine)) {
									return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), true, ""),
											HttpStatus.OK);
								}
							}
						} else {
							return new ResponseEntity<Response>(new Response(HttpStatus.CONFLICT.toString(), false,
									"User with same EmailId Exists"), HttpStatus.OK);
						}
					} else {
						return new ResponseEntity<Response>(new Response(HttpStatus.CONFLICT.toString(), false,
								"User with same contact number already exists"), HttpStatus.OK);

					}
				} else {

					logger.info("Editing Existing User");
					if (!operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_EDIT_USER)) {
						logger.info("Editing Existing User");
						return new ResponseEntity<Response>(
								new Response(HttpStatus.OK.toString(), true, "User Exists with same userName"),
								HttpStatus.CONFLICT);
					}

					if (operationType.equals(MovieRecommenderConstants.OPERATION_TYPE_EDIT_USER)
							&& ifUserExists(strUserName)) {
						if (editUser(userDefine)) {

							return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, ""),
									HttpStatus.OK);

						}
					} else {
						return new ResponseEntity<Response>(
								new Response(HttpStatus.BAD_REQUEST.toString(), false, "User doesnot exist"),
								HttpStatus.OK);
					}

					return new ResponseEntity<Response>(
							new Response(HttpStatus.CONFLICT.toString(), false, "User Exists with same userName"),
							HttpStatus.OK);
					// return new ResponseEntity<>("User Exists with same userName",
					// HttpStatus.CONFLICT);

				}
			} catch (RegisterException e) {

				return new ResponseEntity<Response>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), false,
						"Something went wrong , Contact administrator"), HttpStatus.OK);
				// return new ResponseEntity<>(e.getErrorMessage(),
				// HttpStatus.INTERNAL_SERVER_ERROR);

			}

		} else {
			return new ResponseEntity<Response>(
					new Response(HttpStatus.BAD_REQUEST.toString(), false, "User doesnot exist"), HttpStatus.OK);

		}
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.toString(), true, ""), HttpStatus.OK);
	}*/

}
