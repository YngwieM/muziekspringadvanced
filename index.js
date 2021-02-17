"use strict";
document.getElementById("zoeken").onclick = zoekAlbum;
async function zoekAlbum() {
    verbergFouten();
    const albumResponse = await fetch("http://localhost:8080/albums/" +
        document.getElementById("nummer").value);
    if (albumResponse.ok) {
        const album = await albumResponse.json();
        document.getElementById("album").innerText =
            `${album.album} - ${album.artiest}`;
        const ul = document.getElementById("tracks");
        while (ul.lastChild !== null) {
            ul.lastChild.remove();
        }
        const tracksResponse = await fetch(album._links.tracks.href);
        if (tracksResponse.ok) {
            const tracks = await tracksResponse.json();
            for (const track of tracks) {
                const li = document.createElement("li");
                li.innerText = `${track.naam} ${track.tijd}`;
                ul.appendChild(li);
            }
        } else {
            document.getElementById("technischeFout").style.display = "block";
        }
    } else {
        if (albumResponse.status === 404) {
            document.getElementById("albumNietGevondenFout").style.display = "block";
        } else {
            document.getElementById("technischeFout").style.display = "block";
        }
    }
}
function verbergFouten() {
    for (const div of document.querySelectorAll(".fout")) {
        div.style.display = "none";
    }
}
