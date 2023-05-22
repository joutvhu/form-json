## Setup

```c#
builder.Services
    .AddControllers(options =>
    {
        options.AddFormJsonModelBinderProvider();
    });
```

## Using

Add `[FormJson]` attribute before the parameter

```c#
[HttpPost]
public async Task<PropertyResult> CreateProperty([FormJson] PropertyRequest data)
{
    ...
}
```
