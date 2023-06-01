# Welcome to app-yalisto-android

![GithubActions](https://github.com/afoxplus/app-yalisto-android/actions/workflows/android_publish.yml/badge.svg?branch=master) ![SonarCloud](https://sonarcloud.io/api/project_badges/measure?project=afoxplus-app-yalisto-android&metric=alert_status)

app-yalisto-android It is the bookstore that is in charge of showing the information of products, orders, restaurants, etc. in the home.

## Setup

Create gradle.properties file in the root of your user's .gradle:

 ``` text 
 REPO_USERID_AFOXPLUS=****  
 REPO_TOKEN_AFOXPLUS=****  
 SONARCLOUDTOKEN=****   
 IS_LOCAL=true
 SIGNING_KEY_ALIAS_YALISTO=****
 SIGNING_KEY_PASSWORD_YALISTO=****
 SIGNING_STORE_PASSWORD_YALISTO=****
 ```  

Run the following git commands:

```bash  
git submodule init
git submodule update
```  

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)