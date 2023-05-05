using Microsoft.AspNetCore.Mvc;

namespace Joutvhu.AspNet.FromJson
{
    public static class FormJsonExtension
    {
        public static MvcOptions AddFormJsonModelBinderProvider(this MvcOptions options)
        {
            options.ModelBinderProviders.Insert(0, new FormJsonModelBinderProvider());
            return options;
        }
    }
}