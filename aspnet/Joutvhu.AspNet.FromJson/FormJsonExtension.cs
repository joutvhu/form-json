using Microsoft.AspNetCore.Mvc;

namespace Joutvhu.AspNet.FromJson
{
    /// <summary>
    /// The extension to add <see cref="T:Joutvhu.AspNet.FromJson.FormJsonModelBinderProvider"/>
    /// </summary>
    public static class FormJsonExtension
    {
        /// <summary>
        /// Insert a <see cref="T:Joutvhu.AspNet.FromJson.FormJsonModelBinderProvider"/> into model binder provider list.
        /// </summary>
        /// <param name="options">The <see cref="T:Microsoft.AspNetCore.Mvc.MvcOptions"/></param>
        /// <returns>The <see cref="T:Microsoft.AspNetCore.Mvc.MvcOptions"/></returns>
        public static MvcOptions AddFormJsonModelBinderProvider(this MvcOptions options)
        {
            options.ModelBinderProviders.Insert(0, new FormJsonModelBinderProvider());
            return options;
        }
    }
}