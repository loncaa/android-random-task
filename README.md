Na formi postoji kontrola za unos adrese web stranice i ispod nje button.
Pritiskom na button dohvaća se sadržaj web stranice i izračunava njegov hash (bilo koji).
Ako je za unesenu stranicu već otprije izračunat hash ne treba je dohvaćati iznova nego samo pročitati spremljeni podatak i prikazati poruku. U tom slučaju treba i onemogućiti button na 5 sekundi.
-ako prvi byte hasha paran broj treba ga spremiti u bazu.
-ako je neparan treba ga spremiti u SharedPreferences.
-po završetku treba prikazati poruku koja sadržava adresu, hash i mjesto gdje je pohranjen //VIEW
-ako je došlo do greške prikazati odgovarajuću poruku //VIEW
