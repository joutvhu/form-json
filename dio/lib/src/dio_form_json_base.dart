import 'dart:convert';
import 'dart:io';

import 'package:dio/dio.dart';

class FormJson extends FormData {
  FormJson.fromObject(
    Object value, [
    bool camelCaseContentDisposition = false,
  ]) : super(camelCaseContentDisposition: camelCaseContentDisposition) {
    int index = 0;
    var data = json.encode(
      value,
      toEncodable: (object) {
        if (object is File) {
          object = MultipartFile.fromFile(object.path);
        }
        if (object is MultipartFile) {
          var key = 'file_$index';
          index++;
          files.add(MapEntry(key, object));
          return key;
        }
        return null;
      },
    );
    fields.add(MapEntry('data', data ?? ''));
  }
}

class DioFormJsonInterceptor extends Interceptor {
  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    if (options.contentType == 'multipart/form-json') {
      options.contentType = 'multipart/form-data';
      if (options.data != null) {
        options.data = FormJson.fromObject(options.data);
      }
    } else if (options.extra['form-json'] == true && options.data != null) {
      options.data = FormJson.fromObject(options.data);
    }
    handler.next(options);
  }
}
