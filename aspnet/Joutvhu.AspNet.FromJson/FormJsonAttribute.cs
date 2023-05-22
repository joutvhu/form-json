using System;
using Microsoft.AspNetCore.Mvc.ModelBinding;

namespace Joutvhu.AspNet.FromJson
{
    /// <summary>
    /// Specifies that a parameter should be bound using form-data with json data in the request body.
    /// </summary>
    [AttributeUsage(AttributeTargets.Parameter, AllowMultiple = false, Inherited = true)]
    public class FormJsonAttribute : Attribute, IBindingSourceMetadata
    {
        /// <inheritdoc />
        public BindingSource BindingSource => BindingSource.Form;
    }
}