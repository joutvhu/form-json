## Setup

- Install dependency

```
implementation 'com.github.joutvhu:spring-form-json:1.30.0'
```

```
<dependency>
    <groupId>com.github.joutvhu</groupId>
    <artifactId>spring-form-json</artifactId>
    <version>1.30.0</version>
</dependency>
```

- Version compatibility matrix

| Spring Form JSON Version | Spring Boot Version |
|:-------------------------|:-------------------:|
| 1.25.0                   | 2.5.x - 2.7.x       |
| 1.30.0                   | >= 3.0.0             |

- Add `FormJsonMethodArgumentResolver` to resolvers.

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new FormJsonMethodArgumentResolver());
    }
}
```

## Using

- Using annotation `@FormJson` before the parameter.

```java
@PostMapping
public ResponseEntity<PropertyResult> createProperty(@FormJson PropertyRequest data)
{
    ...
}
```
