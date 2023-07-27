## Setup

- Install dependency

```
dotnet add package Joutvhu.AspNet.FromJson --version 1.0.0
```

- Add model binder provider.

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
