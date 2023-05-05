using System;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc.ModelBinding;
using Newtonsoft.Json;

namespace Joutvhu.AspNet.FromJson
{
    class FormJsonConverter : JsonConverter
    {
        private IFormFileCollection _fileCollection;

        public FormJsonConverter(IFormFileCollection fileCollection)
        {
            _fileCollection = fileCollection;
        }

        public override void WriteJson(JsonWriter writer, object? value, JsonSerializer serializer)
        {
        }

        public override object? ReadJson(JsonReader reader, Type objectType, object? existingValue,
            JsonSerializer serializer)
        {
            if (existingValue is IFormFile)
                return existingValue;
            if (reader.Value is IFormFile)
                return reader.Value;
            if (reader.Value is string name)
            {
                var file = _fileCollection.GetFile(name);
                if (file != null)
                    return file;
            }
            else
            {
                throw new UnsupportedContentTypeException($"Unsupported type @{reader.Value.GetType().Name}");
            }

            return null;
        }

        public override bool CanConvert(Type objectType)
        {
            return objectType.IsAssignableTo(typeof(IFormFile));
        }
    }
}