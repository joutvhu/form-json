export interface FileMap {
  [name: string]: Blob;
}

export function serializeFormJson(data: any): FormData | undefined {
  if (data == null)
    return undefined;
  let index = 0;
  const files: FileMap = {};
  const json = JSON.stringify(data, (_key: string, value: any) => {
    if (value == null) {
      return value;
    } else if (value instanceof Blob) {
      const key = `file_${index}`;
      files[key] = value;
      index++;
      return key;
    } else if (value?.toJSON instanceof Function) {
      return value.toJSON();
    } else {
      return value;
    }
  });
  const form: FormData = new FormData();
  form.append('data', json);
  for (const key in files) {
    form.append(key, files[key]);
  }
  return form;
}
