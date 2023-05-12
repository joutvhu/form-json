export class FormJson {
  static serialize<T>(data: T, replacer?: (key: string, value: any) => any, space?: string | number): FormData | undefined {
    if (data == null)
      return undefined;
    let index = 0;
    const files: { [name: string]: Blob } = {};
    const json = JSON.stringify(data, (_key: string, value: any) => {
      if (value == null) {
        return value;
      } else if (value instanceof Blob) {
        const key = `file_${index}`;
        files[key] = value;
        index++;
        return key;
      } else if (replacer != null) {
        return replacer(_key, value);
      } else if (value?.toJSON instanceof Function) {
        return value.toJSON();
      } else {
        return value;
      }
    }, space);
    const form: FormData = new FormData();
    form.append('data', json);
    for (const key in files) {
      form.append(key, files[key]);
    }
    return form;
  }

  static deserialize<T>(data: FormData, reviver?: (key: string, value: any) => any): T | undefined {
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
        }
      }
      if (reviver != null) {
        return reviver(_key, value);
      } else {
        return value;
      }
    });
    return value as T;
  }
}
