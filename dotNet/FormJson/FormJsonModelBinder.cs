using System;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc.ModelBinding;
using Newtonsoft.Json;

namespace Joutvhu.AspNet.FromJson
{
    public class JsonDataModelBinder : IModelBinder
    {
        public async Task BindModelAsync(ModelBindingContext bindingContext)
        {
            if (bindingContext == null)
            {
                throw new ArgumentNullException(nameof(bindingContext));
            }

            var httpContext = bindingContext.HttpContext;
            var request = httpContext.Request;
            var form = request.Form;

            if (form.ContainsKey("data"))
            {
                string body = form["data"];
                var converter = new FormJsonConverter(form.Files);
                var settings = new JsonSerializerSettings();
                settings.Converters.Add(converter);
                var model = JsonConvert.DeserializeObject(body, bindingContext.ModelType, settings);
                bindingContext.Model = model;
                bindingContext.Result = ModelBindingResult.Success(model);
            }
            else
            {
                bindingContext.Model = null;
                bindingContext.Result = ModelBindingResult.Success(null);
            }
        }
    }
}