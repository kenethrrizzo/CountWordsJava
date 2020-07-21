import java.util.Scanner;

public class Lapiz {
    int tamanolapiz;
    int tamanoPalabras;
    String frase;
    Scanner sc = new Scanner(System.in);

    public Lapiz() {
        this.tamanoPalabras = 0;
        this.tamanolapiz = 0;
    }

    public void elegirTamano(int opcion) {
        switch (opcion) {
            case 1:
                this.tamanolapiz = 10;
                break;
            case 2:
                this.tamanolapiz = 20;
                break;
            case 3:
                this.tamanolapiz = 30;
                break;
            default:
                System.out.println("Solo puede seleccionar del 1 al 3.\nVuelva a intentarlo.");
                int nuevaOpcion = sc.nextInt();
                this.elegirTamano(nuevaOpcion);
        }
    }

    /*
     * Tiene una frase como parámetro, toma ese string y guarda las palabras en un
     * array de strings con el método split(), luego guardamos el tamaño del array
     * en una variable local llamado tamanoPalabras Mostramos los detalles del lapiz
     * y preguntamos si quiere ingresar una nueva frase, si es así la concatenamos
     * con la variable local para poder contar todas las palabras.
     */
    public void contar(String frase) {
        String[] palabras = frase.split(" ");
        this.tamanoPalabras += palabras.length;
        System.out.println(this.tamanoPalabras + " palabras escritas.\n" + this.tamanolapiz + " tamaño del lápiz.\n"
                + (this.tamanolapiz - this.tamanoPalabras) + " palabras restantes por escribir.");
        if ((this.tamanolapiz - this.tamanoPalabras) != 0) {
            System.out.println(
                    "¿Desea seguir escribiendo? Ingrese s para seguir escribiendo, o cualquier otra tecla para salir.");
            String conf = sc.nextLine();
            switch (conf) {
                case "s":
                    escribir();
                    break;
                default:
                    System.out.println("¡Está bien!" + "\n\n");
                    this.menuPrincipal();
                    break;
            }
        } else {
            System.out.println("El lápiz ha llegado a su límite! \nYa no puede escribir más.\n\n");
            this.menuPrincipal();
        }

    }

    public void escribir() {
        System.out.println("Escriba:");
        sc.next();
        this.frase = sc.nextLine();
        String[] fraseArr = this.frase.split(" ");
        System.out.println(fraseArr.length);
        if ((this.tamanolapiz - this.tamanoPalabras) < fraseArr.length) {
            this.frase = null;
            System.out.println("Sobrepasó la capacidad del lápiz!\nEscriba una nueva frase con "
                    + (this.tamanolapiz - this.tamanoPalabras) + " palabras o menos.");
            this.escribir();
        }
        if ((this.tamanolapiz - this.tamanoPalabras) == 0) {
            this.contar(this.frase);
            System.out.println("El lápiz ha llegado a su límite! \nYa no puede escribir más.\n\n");
            this.menuPrincipal();
        } else {
            this.contar(this.frase);
        }
    }

    public void mostrarInfo() {
        System.out.println("Tamaño total del lapiz: " + this.tamanolapiz);
        System.out.println("Tamaño restante del lapiz: " + (this.tamanolapiz - this.tamanoPalabras));
        System.out.println("Palabras escritas: " + this.tamanoPalabras);
    }

    public void menuPrincipal() {
        System.out.println("1. Escribir\n2. Info. del lápiz\n3. Salir");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                if (this.tamanolapiz == 0) { /*
                                              * Si ya elegimos un tamaño del lapiz, no nos muestra la pantalla de
                                              * elegirTamano()
                                              */
                    System.out.println("Elija el tamaño del lápiz:");
                    int tam = sc.nextInt();
                    this.elegirTamano(tam);
                    this.escribir();
                    break;
                } else {
                    if ((this.tamanolapiz - this.tamanoPalabras) == 0) {
                        System.out.println("El lápiz ha llegado a su límite! \nYa no puede escribir más.\n\n");
                        this.menuPrincipal();
                        break;
                    } else {
                        this.escribir();
                        break;
                    }
                }
            case 2:
                this.mostrarInfo();
                System.out.println("Presione enter para ir al menú principal.");
                sc.next();
                this.menuPrincipal();
                break;
            case 3:
                System.out.println("Adioss!!!");
                break;
            default:
                System.out.println("Solo puede elegir del 1 al 3.\nVuelva a intentarlo.");
                this.menuPrincipal();
        }
    }
}