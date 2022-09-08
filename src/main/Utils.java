public final class Utils {
    private Utils() {
    }

    public static MainStudenti[] copy(MainStudenti[] org) {
        MainStudenti[] copy = new MainStudenti[org.length];
        int i = 0;
        System.out.println("lungime copie" + org.length);
        int aux = 1000;
        while (i < org.length && org[i] != null) {
            //if (!((i+2) <= org.length - 1)) {
            if (i > 1) {
                if (copy[i - 1].getCnp().equals(org[i].getCnp())
                ) {
                    aux = i;
                    break;
                }
            }
            System.out.println(org[i].getCnp() + "in bucla");
            copy[i] = org[i];

            i++;
            //}
            //    else { & (org[i+1]!=null)
            //     copy[i]=null;}

        }
        //       }
        //      }
        System.out.println(aux + "nr magic");
//        for (int j = aux; j < copy.length - 1; j++) {
//            copy[j].setCnp(null);
//        }
        return copy;
    }
}

