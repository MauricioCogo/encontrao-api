// Definindo os dados diretamente no arquivo main.js
const presentations = [
    {
        "order": 1,
        "date": "2024-11-15T20:30:00",
        "entrance": "Estação de Lembranças",
        "tdance1": "Balaio",
        "tdance2": "Quero Mana",
        "tdance3": "Chote Carreirinho",
        "exit": "",
        "biritiva1": "",
        "biritiva2": "",
        "biritiva3": "",
        "campus": "IFC - Santa Rosa do Sul",
        "entity": "GTC Danças e Andanças"
    },
    {
        "order": 2,
        "date": "2024-11-15T20:30:00",
        "entrance": "???",
        "tdance1": "Sarrabalho",
        "tdance2": "Chote de Duas Damas",
        "tdance3": "",
        "exit": "???",
        "biritiva1": "",
        "biritiva2": "",
        "biritiva3": "",
        "campus": "IFRS - Bento Gonçalves",
        "entity": "DTG Cultura Sem Fronteira"
    },
    {
        "order": 3,
        "date": "2024-11-15T20:30:00",
        "entrance": "Eu o baio e o temporal",
        "tdance1": "Rancheira de Carreirinha",
        "tdance2": "Anú",
        "tdance3": "",
        "exit": "Paleteada",
        "biritiva1": "Danças dos Facões",
        "biritiva2": "Chico do Porrete",
        "biritiva3": "",
        "campus": "IFRS - Farroupilha",
        "entity": "DTG Raízes da Cultura"
    },
    {
        "order": 4,
        "date": "2024-11-15T20:30:00",
        "entrance": "Romance de Além Mar",
        "tdance1": "Chico Sapateado",
        "tdance2": "Chimarrita",
        "tdance3": "Meia-Canha",
        "exit": "Batismo de Fogo",
        "biritiva1": "",
        "biritiva2": "",
        "biritiva3": "",
        "campus": "IFC - Videira",
        "entity": "CTG Herdeiros do Pago"
    },
    {
        "order": 5,
        "date": "2024-11-16T19:30:00",
        "entrance": "O canto do Carreteiro",
        "tdance1": "Balaio",
        "tdance2": "Tatu de Castanhola",
        "tdance3": "",
        "exit": "",
        "biritiva1": "",
        "biritiva2": "",
        "biritiva3": "",
        "campus": "IFFar - Santa Rosa",
        "entity": "Grupo de Danças Sentinela Farroupilha"
    },
    {
        "order": 6,
        "date": "2024-11-16T19:30:00",
        "entrance": "Chacarera",
        "tdance1": "Caranguejo",
        "tdance2": "Chimarrita",
        "tdance3": "Tatu de Castanholas",
        "exit": "",
        "biritiva1": "",
        "biritiva2": "",
        "biritiva3": "",
        "campus": "IFC - Rio do Sul",
        "entity": "CTG Rincão dos Guris"
    },
    {
        "order": 7,
        "date": "2024-11-16T19:30:00",
        "entrance": "",
        "tdance1": "Chote de Duas Damas",
        "tdance2": "Tatú de Castanholata",
        "tdance3": "Valsa de Mão Trocada",
        "exit": "Estação das Lembranças",
        "biritiva1": "",
        "biritiva2": "",
        "biritiva3": "",
        "campus": "IFRS - Sertão",
        "entity": "CTG Tropeiros da Cultura"
    },
    {
        "order": 8,
        "date": "2024-11-16T19:30:00",
        "entrance": "",
        "tdance1": "Pot-porri de danças tradicionais",
        "tdance2": "",
        "tdance3": "",
        "exit": "",
        "biritiva1": "Danças dos Facões",
        "biritiva2": "",
        "biritiva3": "",
        "campus": "IFFar - Santo Augusto",
        "entity": "DTG Sinuelo Farroupilha"
    },
    {
        "order": 9,
        "date": "2024-11-16T19:30:00",
        "entrance": "De tanto pelear",
        "tdance1": "Tatu de Castanholas",
        "tdance2": "Balaio",
        "tdance3": "",
        "exit": "De tanto pelear/Grupo Rodeio",
        "biritiva1": "",
        "biritiva2": "",
        "biritiva3": "",
        "campus": "IFFar - Alegrete",
        "entity": "DTG Herança Farrapa"
    },
    {
        "order": 10,
        "date": "2024-11-16T19:30:00",
        "entrance": "Vitrola",
        "tdance1": "Anu",
        "tdance2": "Tatu de Castanholhas",
        "tdance3": "Maçanico",
        "exit": "Esse é o nosso lema",
        "biritiva1": "Chula",
        "biritiva2": "Boleadeiras",
        "biritiva3": "",
        "campus": "IFFar - SVS",
        "entity": "NTG Trempe da Saudade"
    }
];

// Função para preencher as apresentações no HTML
// Função para renderizar as apresentações com uma estrutura HTML similar ao exemplo
const renderPresentations = () => {
    const presentationList = document.getElementById('presentation-list');

    // Limpa qualquer conteúdo anterior
    presentationList.innerHTML = '';

    presentations.forEach(presentation => {
        // Cria o container para cada apresentação
        const card = document.createElement('div');
        card.style = "border: 1px solid #ccc; padding: 16px; margin: 16px 0; border-radius: 8px;";

        // Cabeçalho com título e subtítulo
        const header = document.createElement('div');
        const title = document.createElement('h3');
        title.textContent = presentation.entity;
        const subtitle = document.createElement('p');
        subtitle.textContent = presentation.campus;
        header.appendChild(title);
        header.appendChild(subtitle);
        card.appendChild(header);

        // Exibe a data formatada
        const date = document.createElement('p');
        const formattedDate = new Date(presentation.date).toLocaleString('pt-BR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric'
        });
        date.textContent = `Data: ${formattedDate}`;
        card.appendChild(date);

        // Se houver entrada, exibe a seção "Entrada"
        if (presentation.entrance) {
            const entranceTitle = document.createElement('h4');
            entranceTitle.textContent = 'Entrada:';
            const entrance = document.createElement('p');
            entrance.textContent = presentation.entrance;
            card.appendChild(entranceTitle);
            card.appendChild(entrance);
        }

        // Se houver danças tradicionais, exibe a seção "Danças Tradicionais"
        const tdanceTitle = document.createElement('h4');
        tdanceTitle.textContent = 'Danças Tradicionais:';
        card.appendChild(tdanceTitle);
        const tdance1 = document.createElement('p');
        tdance1.textContent = presentation.tdance1;
        card.appendChild(tdance1);
        if (presentation.tdance2) {
            const tdance2 = document.createElement('p');
            tdance2.textContent = presentation.tdance2;
            card.appendChild(tdance2);
        }
        if (presentation.tdance3) {
            const tdance3 = document.createElement('p');
            tdance3.textContent = presentation.tdance3;
            card.appendChild(tdance3);
        }

        // Se houver saída, exibe a seção "Saída"
        if (presentation.exit) {
            const exitTitle = document.createElement('h4');
            exitTitle.textContent = 'Saída:';
            const exit = document.createElement('p');
            exit.textContent = presentation.exit;
            card.appendChild(exitTitle);
            card.appendChild(exit);
        }

        // Adiciona o card à lista
        presentationList.appendChild(card);
    });
};

// Chama a função para renderizar as apresentações quando a página carrega
document.addEventListener('DOMContentLoaded', renderPresentations);

