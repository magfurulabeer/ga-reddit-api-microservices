![Header](https://github.com/magfurulabeer/ga-reddit-api-microservices/blob/assets/header.png)

GA Reddit API Microservices is the Capstone project by Grant Kopplin and Magfurul Abeer for General Assembly's Cognizant Java Academy. While it is a Reddit-like web application, this repository only contains the backend API for it. There is a focus on DevOps with Docker, PWS, Jenkins, and the ELK stack.
[Learn more about our project by visiting our wiki!](https://github.com/magfurulabeer/ga-reddit-api-microservices/wiki)

### Architecture
>![Image of Architecture](https://github.com/magfurulabeer/ga-reddit-api-microservices/blob/master/docs/assets/architecture-diagram.png)

>Our Architecture is quite standard. When the client sends a request to our backend (in this case, through the frontend application), it goes to our API Gateway (Netflix Zuul) first and then goes to our Service Discovery (Netflix Eureka) which then locates one of three services: Users, Posts, or Comments. All 3 services, as well as our API Gateway, use and depend on our shared Postgres Database.

## Technologies Used
>* Pivotal Tracker - User Stories
>* Maven - Dependency Manager
>* Spring Boot - Backend Framework
>* Hibernate - ORM
>* Feign - HTTP Client
>* RabbitMQ - Messaging Queue
>* Jenkins - CI/CD Server
>* Elasticsearch/ Logstash/ Kibana - Monitoring
>* PostgreSQL - Database
>* Docker - Containers
>* Docker Compose - Container Orchestration 
>* Netflix Zuul - API Gateway
>* Netflix Eureka - Service Registry
>* IntelliJ IDEA - IDE
>* Sketch - Image Editing

### Pivotal Tracker
>https://www.pivotaltracker.com/n/projects/2416893

### ERD
>![Image of ERD](https://github.com/magfurulabeer/ga-reddit-api-monolith/blob/master/erd-final.png)

### Challenges

>One of the biggest blockers we've dealt with was the application working on one machine but not the other. On the problem machine, there would always be a missing microservice. We had originally misdiagnosed this error and assumed that there was something wrong with the Posts Microservice since on the first day, that was the one that wouldn't be up when we ran `docker-compose up`. We worked around this by exclusively pair programming as we had no other choice. We later found out that it was a docker configuration issue where 4GiB of memory wasn't enough. After increasing it to 8GiB, it worked as expected.

>During the frontend integration, we realized that the frontend was expecting the Post and Comment JSON response to include a user property and a nested username property. This didn't match up with our Post and Comment models which directly had a username property. We solved this issue by creating a custom serializer with Jackson which would return the JSON in the format the frontend expected.

>One of the challenges we faced was having microservices communicate with each. We had a handful of options to choose from including RestTemplate but we ultimately decided on Feign. It was a bit excessive for this project but it was simple to set up.

>Another issue we had on the last day was during our attempt at deployment. For some reason, we weren't able to build our applications with `mvn clean package`. We had checked with other groups and it seemed to be an issue specifically to us. We eventually managed to build them by executing the commands directly in the docker container instances using `docker exec instance_name mvn clean package` which worked exactly as desired.

>The last issue we had which was also during deployment had to do with not being able to deploy our backend to Pivotal Cloud Foundry using a root-level manifest file. After lots of troubleshooting, we realized that it was an OOM issue and increasing the memory in Docker fixed it. This is what helped us solve one of the issues above as well. The interesting thing is when we ran a command to inspect what was going on, it specifically said it was not an OOM exception.

### Documentation
>JavaDocs are currently available for the users-api in the docs branch. Due to it's monotonous and tedious nature, only users-api was documented. With more time, we could easily complete the full documentations. Swagger is set up in the docs branch as well, but there are no annotations for it in the code currently.
