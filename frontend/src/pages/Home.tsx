import {Container, Typography } from "@mui/material";
import React from "react";
import './Home.css'


export default function Home() {
    return (


            <section className={"home-text"}>
                <main>
                    <div>
                        <Container maxWidth="sm">
                            <Typography variant = "h2" align={"center"} color={"textPrimary"} gutterBottom >Home</Typography>
                            <Typography variant = "h5" align={"center"} color={"textPrimary"} gutterBottom paragraph>Wir wissen, dass du Book-Paradise-App bereits kennst, aber es lohnt sich,
                                es zu wiederholen, denn die App macht die Nutzung noch einfacher!
                                Du kannst ein Buch zu Ihrer Favoritenliste speichern, in vorgestellten
                                Büchern und Genres stöbern, sehen, was Ihre Freunde lesen!
                                Außerdem kannst du jedes Buch im gesamten Book-Paradise-Katalog suchen,
                                bewerten und rezensieren. <br /> Es ist sogar noch einfacher, den Überblick
                                über all die Bücher zu behalten, die du lesen willst und gelesen hast.
                                Wir finden es auch toll, dass man sich über die Book-Paradise-App
                                mit Hilfe von Nachrichten, Rezensionen, Gruppen und Empfehlungen über
                                Bücher austauschen kann!</Typography>
                        </Container>
                    </div>
                </main>




            </section>
    )

}