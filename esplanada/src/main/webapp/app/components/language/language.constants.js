(function () {
    'use strict';

    angular
        .module('colegioEsplanadaApp')

        /*
         Languages codes are ISO_639-1 codes, see http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
         They are written in English to avoid character encoding issues (not a perfect solution)
         */
        .constant('LANGUAGES', [
            'en',
            'ca',
            'zh-cn',
            'zh-tw',
            'cs',
            'da',
            'nl',
            'fr',
            'gl',
            'de',
            'el',
            'hi',
            'hu',
            'it',
            'ja',
            'ko',
            'mr',
            'pl',
            'pt-br',
            'pt-pt',
            'ro',
            'ru',
            'sk',
            'es',
            'sv',
            'tr',
            'ta'
            // jhipster-needle-i18n-language-constant - JHipster will add/remove languages in this array
        ]
    );
})();
