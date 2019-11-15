# GA Reddit API Microservices

### Pivotal Tracker
https://www.pivotaltracker.com/n/projects/2416893

### ERD
![Image of ERD](https://github.com/magfurulabeer/ga-reddit-api-monolith/blob/master/erd-final.png)

### Challenges

During the frontend integration, we realized that the frontend was expecting the Post and Comment JSON response to include a user property and a nested username property. This didn't match up with our Post and Comment models which directly had a username property. We solved this issue by creating a custom serializer with Jackson which would return the JSON in the format the frontend expected.

One of the challenges we faced was having microservices communicate with each. We had a handful of options to choose from including RestTemplate but we ultimately decided on Feign. It was a bit excessive for this project but it was simple to set up.
