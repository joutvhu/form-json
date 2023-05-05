## Setup

```c#
builder.Services
    .AddControllers(options =>
    {
        options.AddFormJsonBinderProvider();
    });
```

# Using

Add `[FormJson]` before the param