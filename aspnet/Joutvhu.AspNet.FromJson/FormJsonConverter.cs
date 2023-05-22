using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc.ModelBinding;
using Newtonsoft.Json;

namespace Joutvhu.AspNet.FromJson
{
    internal class FormJsonConverter : JsonConverter
    {
        private readonly IList<IFormFile> _files;
        private readonly IFormFileCollection? _fileCollection;

        public FormJsonConverter(IFormFileCollection fileCollection)
        {
            _fileCollection = fileCollection;
            _files = fileCollection.ToList();
        }

        public FormJsonConverter(IList<IFormFile> files)
        {
            _fileCollection = null;
            _files = files;
        }

        public override void WriteJson(JsonWriter writer, object? value, JsonSerializer serializer)
        {
            if (value is IFormFile formFile)
            {
                var key = $"file_{_files.Count}";
                _files.Add(new ProxyFormFile(key, formFile));
                writer.WriteValue(key);
            }
        }

        public override object? ReadJson(JsonReader reader, Type objectType, object? existingValue, JsonSerializer
            serializer)
        {
            if (existingValue is IFormFile)
                return existingValue;
            if (reader.Value is IFormFile)
                return reader.Value;
            if (reader.Value is string name)
            {
                var file = _fileCollection?.GetFile(name) ??
                           _files.FirstOrDefault(formFile => formFile.Name == name);
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