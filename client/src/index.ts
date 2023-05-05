export interface FileMap {
  [name: string]: Blob;
}

export function serializeFormJson<T>(data: T): FormData | undefined {
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

export function deserializeFormJson<T>(data: FormData): T | undefined {
  if (data == null)
    return undefined;
  const json: any = data.get('data');
  if (json == null)
    return undefined;
  if (typeof json !== 'string')
    throw new Error('The data is not a JSON string');
  const value = JSON.parse(json, (_key: string, value: any) => {
    if (typeof value === 'string') {
      if (/^file_\d$/.test(value)) {
        const file = data.get(value);
        if (file != null)
          return file;
        else
          return value;
      }
    }
    return value;
  });
  return value as T;
}
