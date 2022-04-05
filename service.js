
    if ('serviceWorker' in navigator) {
      window.addEventListener('load', function () {
          this.navigator.serviceWorker.register('./service.js').then(function (registeration) {
              console.log('ServiceWorker registration successful with scope', registeration.scope);
          }, function(err) {
              console.log('Serviceworker registration failed', err);
          });
      })
    }

    //  ***********************************************************************************************

    var CACHE_NAME = 'my-site-cache-v1';
    var UrlsToCache = [
        '/',
        'homepage.html'
    ];
    
    self.addEventListener('install', function(event){
        event.WaitUntil(
            caches.open(CACHE_NAME)
            .then(function (cache){
                console.log('Open cache');
                return cache.addAll(UrlsToCache);
            })
        )
    });


// *********************************************************



self.addEventListener('fetch', function(event){
    event.respondWith(
        caches.match(event.request)
        .then(function (response){
            if (response){
                return response;
            }
            return fetch(event.request);
        })
    )
});