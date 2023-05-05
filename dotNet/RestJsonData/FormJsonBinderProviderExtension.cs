using Microsoft.AspNetCore.Mvc;

namespace Joutvhu.AspNet.RestJsonData
{
    public static class FormJsonBinderProviderExtension
    {
        public static MvcOptions AddFormJsonBinderProvider(this MvcOptions options)
        {
            options.ModelBinderProviders.Insert(0, new JsonDataModelBinderProvider());
            return options;
        }
    }
}