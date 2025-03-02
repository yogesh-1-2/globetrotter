<img src="https://drive.google.com/file/d/1JCaHqaw3kvDrZpvYGIdLiW149xzy7g3G/view?usp=sharing" alt="Globetrotter Logo" width="200">

JAVA 17

HOW TO START the application
run : 
    1. mvn clean install
    2. mvn spring-boot:run

API curl:

1. Register user:
   curl --location --request POST 'localhost:8081/users/register?username=headout' \
      --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
2. Get Random game Not played by the user:
   curl --location 'localhost:8081/globetrotter/city/random?userName=headout' \
      --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
3. Get Hint for the game: 
   curl --location 'localhost:8081/globetrotter/city/10/hints/1' \
      --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
4. Get Options for the game:
   curl --location 'localhost:8081/globetrotter/city/10/Options' \
      --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
2. Check for Answer: curl --location 'localhost:8081/globetrotter/city/10/check?userId=headout&answer=Berlin' \
   --header 'Cookie: JSESSIONID=F8FAB647E856BB37CC1010A8C9F508A1'
