## Setup

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