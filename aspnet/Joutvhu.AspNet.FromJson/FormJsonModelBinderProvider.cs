using System;
using Microsoft.AspNetCore.Mvc.ModelBinding;
using Microsoft.AspNetCore.Mvc.ModelBinding.Binders;
using Microsoft.AspNetCore.Mvc.ModelBinding.Metadata;

namespace Joutvhu.AspNet.FromJson
{
    /// <inheritdoc />
    public class FormJsonModelBinderProvider : IModelBinderProvider
    {
        /// <inheritdoc />
        public IModelBinder GetBinder(ModelBinderProviderContext context)
        {
            if (context == null)
            {
                throw new ArgumentNullException(nameof(context));
            }

            if (context.Metadata is DefaultModelMetadata metadata &&
                context.Metadata.BindingSource == BindingSource.Form)
            {
                bool isFormJson = false;
                foreach (var attribute in metadata.Attributes.Attributes)
                {
                    if (attribute.GetType().Equals(typeof(FormJsonAttribute)))
                    {
                        isFormJson = true;
                        break;
                    }
                }

                if (isFormJson)
                {
                    return new BinderTypeModelBinder(typeof(FormJsonModelBinder));
                }
            }

            return null;
        }
    }
}