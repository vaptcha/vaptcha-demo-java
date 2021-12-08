# VAPTCHA-java-v3  

## Quick Start  
1.Create a VAPTCHA unit and obtainVID and Key,[Click create](https://user-en.vaptcha.com/).

2.replace VID and Key.

Frontend
```html
// vaptcha-sdk/src/main/resources/static/xxx.html

<script>
    vaptcha({
        vid: '***',
        scene: 1,
    }).then(function (vaptchaObj) {
      ...
</script>
```

Backend
```java
// vaptcha-sdk/src/main/java/com/vaptcha/constant/Constant.java

public static final String SecretKey = "****";
public static final String Vid = "****";
```


3.Run Server And Test

click mode: localhost:8080/click  
embed mode: localhost:8080/embed  
invisible mode: localhost:8080/invisible