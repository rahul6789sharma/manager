# FootBall Teams Manager 

## Technologies

1. Java 17
2. Spring Framework Boot v3.x.x
5. Spring Data Jpa
6. H2 dataBase

## Local

- [Service DUS page](https://confluence.hilti.com/pages/viewpage.action?pageId=209251159)
- [Technical documentation](https://confluence.hilti.com/display/pshilti/Smart+search+decision+on+components+and+implementation)

### Build and run

_Do not forget to start docker containers before launching the app._

Run `./gradlew bootRun` - this will run the app with 'local' profile applied.

Run `./gradlew bootRun --args='--spring.profiles.active={profile}'` to build and run application with specific profile.

Run tests locally (starts temporarily docker containers, executes tests, etc.)
`ACTIVE_PROFILE=test ./gradlew test --info`

Profiles are:

` local, dev, qas, preprod `

and for **testing** we use:

` test, gitlab ` (gitlab is used to run test on Gitlab CI).

For local development we can use profile 'local'

### Docker setup

1. Make sure you have installed docker desktop, and it's working correctly.
2. Make sure you have access to package registry (https://git.hilti.com/hdms/hilti-online/hdms-smart-search/-/packages)
3. Make sure you have access to container registry (https://git.hilti.com/hdms/hilti-online/hdms-smart-search/container_registry)


####Default usage
1. Create access token to login to gitlab (go to https://git.hilti.com/-/profile/personal_access_tokens or https://git.hilti.com/hdms/ -> edit profile -> Access Token -> add token with read_api/read_registry/write_registry scopes)
2. Login to gitlab docker (ex: `docker login registry-git.hilti.com -u userfirstname.userlastname -p generated_token_xyz`)
3. Then `cd /hdms-smart-search/docker/default` and `docker-compose up` - this will pull the images from gitlab docker registry and a good way to check if container can be started

Container should be created/started

## CICD

Pipelines:
https://git.hilti.com/hdms/hilti-online/hdms-smart-search/-/pipelines

## Code Formatter
https://confluence.hilti.com/pages/viewpage.action?spaceKey=pshilti&title=How+to+setup+code-formatter