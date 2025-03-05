function changeLanguage(lang) {
    const currentUrl = window.location.pathname + window.location.search;
    window.location.href = `/locale/${lang}?redirect=` + encodeURIComponent(currentUrl);
}

function toggleDropdown(element) {
    element.classList.toggle("active");
}
