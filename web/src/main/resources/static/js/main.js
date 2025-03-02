function changeLanguage(lang) {
    const currentUrl = window.location.pathname + window.location.search;
    window.location.href = `/locale/${lang}?redirect=` + encodeURIComponent(currentUrl);
}

function toggleDropdown(element) {
    element.classList.toggle("active");
}

function goBackWithLang() {
    // Leer la cookie 'frontend_lang'
    const lang = getCookie('frontend_lang');

    // Redirigir a la página anterior o recargar la página con el idioma correcto
    if (lang) {
        // Aquí puedes ajustar el idioma de tu página usando la cookie si es necesario
        // (ejemplo: establecer el idioma en un selector de idioma o en una URL)
        window.location.href = window.location.pathname + '?lang=' + lang;
    } else {
        // Si no hay cookie, simplemente usa history.back()
        history.back();
    }
}

// Función para obtener el valor de una cookie por su nombre
function getCookie(name) {
    const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
    return match ? match[2] : null;
}