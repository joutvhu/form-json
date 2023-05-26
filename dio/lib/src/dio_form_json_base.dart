import 'dart:convert';
import 'dart:io';

import 'package:dio/dio.dart';

FormData toFormJson(
  Object value, [
  bool camelCaseContentDisposition = false,
]) {
  var result = FormData(
    camelCaseContentDisposition: camelCaseContentDisposition,
  );
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
        result.files.add(MapEntry(key, object));
        return key;
      }
      return null;
    },
  );
  result.fields.add(MapEntry('data', data ?? ''));
  return result;
}
