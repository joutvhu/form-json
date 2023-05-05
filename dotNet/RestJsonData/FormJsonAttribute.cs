﻿using System;
using Microsoft.AspNetCore.Mvc.ModelBinding;

namespace Joutvhu.AspNet.RestJsonData
{
    [AttributeUsage(AttributeTargets.Parameter, AllowMultiple = false, Inherited = true)]
    public class FormJsonAttribute : Attribute, IBindingSourceMetadata
    {
        public BindingSource BindingSource => BindingSource.Form;
    }
}