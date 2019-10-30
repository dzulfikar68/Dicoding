package com.digitcreativestudio.moviecatalogueuiux;

import java.util.ArrayList;

public class MoviesData {

    public static String[][] data = new String[][]{
            {"The Sixth Sense",
                    "https://image.tmdb.org/t/p/w300_and_h450_bestv2/imps263dHNe3SuoaAJORZyNTdDT.jpg",
                    "mistery, thriller, drama",
                    "August 6, 1999",
                    "A psychological thriller about an eight year old boy named Cole Sear who believes he can see into the world of the dead. A child psychologist named Malcolm Crowe comes to Cole to help him deal with his problem, learning that he really can see ghosts of dead people."},
            {"The Machinist",
                    "https://image.tmdb.org/t/p/w300_and_h450_bestv2/2tYr40n1ZjbMfYq20ieiAPsSe5.jpg",
                    "thriller, drama",
                    "October 22, 2004",
                    "The Machinist is the story of Trevor Reznik, a lathe-operator who is dying of insomnia. In a machine shop, occupational hazards are bad enough under normal circumstances; yet for Trevor the risks are compounded by fatigue. Trevor has lost the ability to sleep. This is no ordinary insomnia..."},
            {"Mr. Brooks",
                    "https://image.tmdb.org/t/p/w300_and_h450_bestv2/atLwYVquG4YrGEk06tx9tJuTcN5.jpg",
                    "drama, crime, mystery, thriller",
                    "June 1, 2007",
                    "A psychological thriller about a man who is sometimes controlled by his murder-and-mayhem-loving alter ego."},
            {"Misery",
                    "https://image.tmdb.org/t/p/w300_and_h450_bestv2/s0Ot5zUmVF1jSN9WpBHRafvUPld.jpg",
                    "drama, thriller",
                    "November 30, 1990",
                    "Novelist Paul Sheldon crashes his car on a snowy Colorado road. He is found by Annie Wilkes, the \"number one fan\" of Paul's heroine Misery Chastaine. Annie is also somewhat unstable, and Paul finds himself crippled, drugged and at her mercy."},
            {"The Silence of the Lambs",
                    "https://image.tmdb.org/t/p/w300_and_h450_bestv2/qjAyTj2BSth1EQ89vNfo0JYVPFN.jpg",
                    "crime, drama, thriller, horror",
                    "February 14, 1991",
                    "Clarice Starling is a top student at the FBI's training academy. Jack Crawford wants Clarice to interview Dr. Hannibal Lecter, a brilliant psychiatrist who is also a violent psychopath, serving life behind bars for various acts of murder and cannibalism. Crawford believes that Lecter may have insight into a case and that Starling, as an attractive young woman, may be just the bait to draw him out."},
            {"Gone Girl",
                    "https://image.tmdb.org/t/p/w300_and_h450_bestv2/gdiLTof3rbPDAmPaCf4g6op46bj.jpg",
                    "mystery, thriller, drama",
                    "October 3, 2014",
                    "With his wife's disappearance having become the focus of an intense media circus, a man sees the spotlight turned on him when it's suspected that he may not be innocent."},
            {"Se7en",
                    "https://image.tmdb.org/t/p/w300_and_h450_bestv2/8zw8IL4zEPjkh8Aysdcd0FwGMb0.jpg",
                    "crime, mystery, thriller",
                    "September 22, 1995",
                    "Two homicide detectives are on a desperate hunt for a serial killer whose crimes are based on the \"seven deadly sins\" in this dark and haunting film that takes viewers from the tortured remains of one victim to the next. The seasoned Det. Sommerset researches each sin in an effort to get inside the killer's mind, while his novice partner, Mills, scoffs at his efforts to unravel the case."},
            {"Fight Club",
                    "https://image.tmdb.org/t/p/w300_and_h450_bestv2/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg",
                    "drama",
                    "October 15, 1999",
                    "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground fight clubs forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion."},
            {"Primal Fear",
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/qJf2TzE8nRTFbFMPJNW6c8mI0KU.jpg",
                    "crime, drama, mystery, thriller",
                    "April 3, 1996",
                    "An arrogant, high-powered attorney takes on the case of a poor altar boy found running away from the scene of the grisly murder of the bishop who has taken him in. The case gets a lot more complex when the accused reveals that there may or may not have been a third person in the room. The intensity builds when a surprise twist alters everyone's perception of the crime."},
            {"Scream",
                    "https://image.tmdb.org/t/p/w300_and_h450_bestv2/ewi7gYW22t8T3piRvrXO73GlzuL.jpg",
                    "crime, horror, mystery",
                    " December 20, 1996",
                    "A killer known as Ghostface begins killing off teenagers, and as the body count begins rising, one girl and her friends find themselves contemplating the 'rules' of horror films as they find themselves living in a real-life one."},
    };

    public static ArrayList<Movie> getListdata(){
        ArrayList<Movie> list = new ArrayList<>();
        for(String[] aData : data){
            Movie movie = new Movie();
            movie.setTitle(aData[0]);
            movie.setPoster(aData[1]);
            movie.setGenre(aData[2]);
            movie.setRelease(aData[3]);
            movie.setDescription(aData[4]);

            list.add(movie);
        }

        return list;
    }

}
