// main.js
let map;

function initMap() {
    // Criando o mapa
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: -29.7044, lng: -54.6982 },  // Centraliza no ponto inicial
        zoom: 16  // Nível de zoom do mapa
    });

    // Criar os marcadores para cada ponto
    points.forEach(function(point) {
        const marker = new google.maps.Marker({
            position: { lat: point.latitude, lng: point.longitude },
            map: map,
            title: point.name,  // Título do marcador (exibido ao passar o mouse)
            icon: point.icon    // Ícone personalizado
        });

        // Criando um InfoWindow (janela de informação) que aparece quando o marcador é clicado
        const infowindow = new google.maps.InfoWindow({
            content: `<b>${point.name}</b>`  // Exibindo o nome do ponto
        });

        // Quando o marcador for clicado, o InfoWindow será exibido
        marker.addListener('click', function() {
            infowindow.open(map, marker);
        });
    });
}
