var stompClient = null;
var connected = false;
connect()

function connect() {
    if (connected) {
        disconnect()
    }
    connected = true;
    var socket = new SockJS('/game');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected');
        stompClient.subscribe('/topic/board', function(gameState) {
            update(JSON.parse(gameState.body));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    connected = false;
    console.log("Disconnected");
}

function startGame() {
    stompClient.send("/app/newgame", {}, JSON.stringify({}));
}

function selectTile(name) {
    stompClient.send("/app/move", {}, JSON.stringify({'name':name}));
}

function update(gamestate) {
    for (var row of gamestate.chessboard.tiles) {
        for(var tile of row) {
            var element = document.getElementById(tile.name);
            if (tile.selected) {
                element.classList.add("selected");
            } else {
                if (element.classList.contains("selected")) {
                    element.classList.remove("selected");
                }
            }
            if (tile.available) {
                element.classList.add("available");
            } else {
                if (element.classList.contains("available")) {
                    element.classList.remove("available");
                }
            }

            if (tile.piece != null) {
                element.innerHTML = '<img src="img/' + tile.piece.color.substring(0, 1) + tile.piece.symbol + '.png">';
            } else {
                element.innerHTML = '';
            }
        }
    }
}