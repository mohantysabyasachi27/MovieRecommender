# MovieRecommender
• A movie showtime finder

Install Docker.. https://store.docker.com/search?type=edition&offering=community

After Docker is installed : 
# Docker for Linux - You are good to go. 

# Docker for Mac - You can use docker-machine create with the virtualbox driver to create additional local machines.
  Run : $ docker-machine create --driver virtualbox default
        $ docker-machine create
        $ eval $(docker-machine env default)
        $ docker-machine active
        $ docker-machine ip

# Docker for Windows - You can use docker-machine create with the hyperv driver to create additional local machines
  Run : $ docker-machine create --driver hyperv default
        $ docker-machine create
        $ eval $(docker-machine env default)
        $ docker-machine active
        $ docker-machine ip

# For windows & Mac OS: To get the IP address on which the application runs : docker-machine ip 
# For Linux: IP is the localhost

# Step-1 -> Clone the project:
$ git clone https://github.com/mohantysabyasachi27/MovieRecommender/

# Step-2 -> Clone the Submodule - Frontend: 
$ git submodule init
$ git submodule update
                     
# Step - 3 -> Run this : 
$ sudo docker-compose down && mvn clean install

# Step - 4 -> Run the docker containers:
$ sudo docker-compose up --build

# Step - 5 -> Once the docker containers start.
 Open web browser and hit http://{IP}:4200/, where IP is obtained from the above steps. 
