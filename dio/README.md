## Settup

Install dependency

```
dart pub add dio_form_json
```

## Using

Add `DioFormJsonInterceptor` into Dio.

```
dio.interceptors.add(DioFormJsonInterceptor())
```

Set contentType = 'multipart/form-json' or extra['form-json'] = true to convert input data to FormData.
