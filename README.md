# MovieRecommender 

The complete application can be accessed by the URL [http://34.217.104.81:4200](http://34.217.104.81:4200)

(Note: If you want to explore docker, follow below steps.)

<br />
• A movie showtime finder <br />

Install Docker.. https://store.docker.com/search?type=edition&offering=community <br />

After Docker is installed : <br /> <br />

# Docker for Linux - You are good to go. 
<br />

# Docker for Mac - You can use docker-machine create with the virtualbox driver to create additional local machines.
  <br />
  Run : $ docker-machine create --driver virtualbox default <br />
        $ docker-machine create <br />
        $ eval $(docker-machine env default) <br />
        $ docker-machine start <br />
        $ docker-machine active <br />
        $ docker-machine ip <br />
        

# Docker for Windows - You can use docker-machine create with the hyperv driver to create additional local machines
  <br />
  Run : $ docker-machine create --driver hyperv default <br />
        $ docker-machine create <br />
        $ eval $(docker-machine env default) <br />
        $ docker-machine active <br />
        <br />

# For windows & Mac OS: To get the IP address on which the application runs : 
<br />
$ docker-machine ip <br />


# For Linux: To get the IP address on which the application runs : 
<br />
IP is the localhost <br />

# Step-1 -> Clone the project:
<br />
$ git clone https://github.com/mohantysabyasachi27/MovieRecommender/ <br />

# Step-2 -> Clone the Submodule - Frontend: 
<br />
$ cd MovieRecommender
$ git submodule init  <br />
$ git submodule update  <br />
 <br />

# Step - 3 -> Run the docker containers:
<br />
$ sudo docker-compose up --build
<br />
# Step - 4 -> Once the docker containers start.
 <br />
 Open web browser and hit http://{IP}:4200/, where IP is obtained from the above steps. 
