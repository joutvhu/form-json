using System.IO;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace Joutvhu.AspNet.FromJson
{
    /// <summary>
    /// 
    /// </summary>
    internal class ProxyFormFile : IFormFile
    {
        private readonly IFormFile _file;

        /// <summary>
        /// Create a <see cref="T:Joutvhu.AspNet.FromJson.ProxyFormFile"/>
        /// </summary>
        /// <param name="name">The Name</param>
        /// <param name="file">The target <see cref="T:Microsoft.AspNetCore.Http.IFormFile"/></param>
        public ProxyFormFile(string name, IFormFile file)
        {
            Name = name;
            _file = file;
        }

        /// <summary>
        /// Gets the raw Content-Disposition header of the uploaded file.
        /// </summary>
        public string ContentDisposition => _file.ContentDisposition;

        /// <summary>
        /// Gets the raw Content-Type header of the uploaded file.
        /// </summary>
        public string ContentType => _file.ContentType;

        /// <summary>Gets the header dictionary of the uploaded file.</summary>
        public IHeaderDictionary Headers => _file.Headers;

        /// <summary>Gets the file length in bytes.</summary>
        public long Length => _file.Length;

        /// <summary>Gets the name from the Content-Disposition header.</summary>
        public string Name { get; }

        /// <summary>
        /// Gets the file name from the Content-Disposition header.
        /// </summary>
        public string FileName => _file.FileName;

        /// <summary>
        /// Opens the request stream for reading the uploaded file.
        /// </summary>
        public Stream OpenReadStream() => _file.OpenReadStream();

        /// <summary>
        /// Copies the contents of the uploaded file to the <paramref name="target" /> stream.
        /// </summary>
        /// <param name="target">The stream to copy the file contents to.</param>
        public void CopyTo(Stream target) => _file.CopyTo(target);

        /// <summary>
        /// Asynchronously copies the contents of the uploaded file to the <paramref name="target" /> stream.
        /// </summary>
        /// <param name="target">The stream to copy the file contents to.</param>
        /// <param name="cancellationToken"></param>
        public Task CopyToAsync(Stream target, CancellationToken cancellationToken = default) =>
            _file.CopyToAsync(target, cancellationToken);
    }
}