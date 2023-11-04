package contrasenasycarro;

import java.security.SecureRandom;
import javax.swing.JOptionPane;

public class ContrasenasYCarro {

    // Contraseña para operaciones de modificación y eliminación
    private static final String PASSWORD = generatePassword(8, true, 1, true, 1, true, 1, true, 1);

    // Base de datos de automóviles (para simplificar, se utiliza un arreglo)
    private static Car[] cars = new Car[100];
    private static int carCount = 0;

    public static void main(String[] args) {
        // Generar dos códigos
        String code1 = generateCode();
        String code2 = generateCode();

        // Mensaje de bienvenida
        JOptionPane.showMessageDialog(null, "Gracias por ingresar al generador de contraseñas y administración de automóviles. "
                + "Le brindaremos dos códigos y solo debe ingresar uno de ellos para poder ingresar al sistema. "
                + "El sistema permitirá gestionar la información de automóviles.\n\n"
                + "Código 1: " + code1 + "\n"
                + "Código 2: " + code2, "Bienvenido", JOptionPane.INFORMATION_MESSAGE);

        // Mostrar los códigos generados con JOptionPane
        String inputCode = JOptionPane.showInputDialog("Ingrese uno de los siguientes códigos para acceder al sistema:");

        // Validar el código ingresado
        if (inputCode != null && (inputCode.equals(code1) || inputCode.equals(code2))) {
            // Código válido, mostrar el menú de opciones
            showMenu();
        } else {
            // Código no válido, mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, "Código no válido. Acceso denegado.", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void showMenu() {
        while (true) {
            String[] options = {"Visualizar carro", "Agregar carro", "Modificar carro", "Eliminar carro", "Salir"};
            int choice = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menú", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // Visualizar carro
                    viewCar();
                    break;
                case 1: // Agregar carro
                    addCar();
                    break;
                case 2: // Modificar carro
                    if (validatePassword()) {
                        modifyCar();
                    }
                    break;
                case 3: // Eliminar carro
                    if (validatePassword()) {
                        deleteCar();
                    }
                    break;
                case 4: // Salir
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema. Hasta luego.", "Salida", JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
        }
    }

    public static boolean validatePassword() {
        String inputPassword = JOptionPane.showInputDialog("Ingrese la contraseña para continuar con esta operación:");
        return inputPassword != null && inputPassword.equals(PASSWORD);
    }

    public static void viewCar() {
        // Implementa la lógica para visualizar un automóvil
        // Puedes mostrar una lista de automóviles disponibles y permitir al usuario seleccionar uno para ver los detalles
        // Por ejemplo, puedes mostrar los detalles de un automóvil específico en una ventana de diálogo
        String carId = JOptionPane.showInputDialog("Ingrese el ID del automóvil a visualizar:");
        if (carId != null) {
            // Buscar el automóvil en la base de datos y mostrar sus detalles
            Car car = findCarById(carId);
            if (car != null) {
                JOptionPane.showMessageDialog(null, "Detalles del automóvil:\n" +
                        "ID: " + car.getId() + "\n" +
                        "Marca: " + car.getMarca() + "\n" +
                        "Línea: " + car.getLinea() + "\n" +
                        "Modelo: " + car.getModelo() + "\n"
                        // Agrega los demás detalles aquí
                        , "Detalles del Automóvil", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Automóvil no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void addCar() {
        // Implementa la lógica para agregar un automóvil
        // Pide al usuario que ingrese los detalles del automóvil y agrégalo a la base de datos
        // Por ejemplo, puedes mostrar una serie de cuadros de diálogo para ingresar los detalles del automóvil
        String id = JOptionPane.showInputDialog("Ingrese el ID del automóvil:");
        String marca = JOptionPane.showInputDialog("Ingrese la marca del automóvil:");
        String linea = JOptionPane.showInputDialog("Ingrese la línea del automóvil:");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del automóvil:");
        // Agrega más detalles según tus requerimientos

        if (id != null && marca != null && linea != null && modelo != null) {
            Car car = new Car(id, marca, linea, modelo); // Crea una instancia de Car con los detalles ingresados
            cars[carCount] = car;
            carCount++;
            JOptionPane.showMessageDialog(null, "Automóvil agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al agregar el automóvil. Faltan detalles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void modifyCar() {
        // Implementa la lógica para modificar un automóvil
        // Pide al usuario que ingrese el ID del automóvil a modificar y luego permite editar sus detalles
        String carId = JOptionPane.showInputDialog("Ingrese el ID del automóvil a modificar:");
        if (carId != null) {
            Car car = findCarById(carId);
            if (car != null) {
                // Puedes mostrar cuadros de diálogo para que el usuario edite los detalles del automóvil
                String newMarca = JOptionPane.showInputDialog("Nueva marca: (actual: " + car.getMarca() + ")");
                String newLinea = JOptionPane.showInputDialog("Nueva línea: (actual: " + car.getLinea() + ")");
                String newModelo = JOptionPane.showInputDialog("Nuevo modelo: (actual: " + car.getModelo() + ")");
                // Agrega más campos para editar según tus requerimientos

                // Actualiza los detalles del automóvil
                if (newMarca != null) {
                    car.setMarca(newMarca);
                }
                if (newLinea != null) {
                    car.setLinea(newLinea);
                }
                if (newModelo != null) {
                    car.setModelo(newModelo);
                }
                // Actualiza los demás campos según lo que el usuario haya ingresado
                JOptionPane.showMessageDialog(null, "Automóvil modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Automóvil no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void deleteCar() {
        // Implementa la lógica para eliminar un automóvil
        // Pide al usuario que ingrese el ID del automóvil a eliminar y lo elimina de la base de datos
        String carId = JOptionPane.showInputDialog("Ingrese el ID del automóvil a eliminar:");
        if (carId != null) {
            for (int i = 0; i < carCount; i++) {
                if (cars[i] != null && cars[i].getId().equals(carId)) {
                    // Encuentra el automóvil y lo elimina
                    cars[i] = null;
                    JOptionPane.showMessageDialog(null, "Automóvil eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Automóvil no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ... Resto del código para gestionar la clase Car y las operaciones con automóviles

    // Método para buscar un automóvil por su ID
    public static Car findCarById(String carId) {
        for (int i = 0; i < carCount; i++) {
            if (cars[i] != null && cars[i].getId().equals(carId)) {
                return cars[i];
            }
        }
        return null; // Si no se encuentra el automóvil
    }

    // Método para generar códigos
    public static String generateCode() {
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 10; i++) { // Cambia 10 por la longitud deseada del código
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    // Método para generar contraseñas
    public static String generatePassword(int length, boolean restrictMinDigits, int minDigits,
            boolean restrictMinUpperCaseLetters, int minUpperCaseLetters,
            boolean restrictMinLowerCaseLetters, int minLowerCaseLetters,
            boolean restrictMinNonAlphanumericCharacters, int minNonAlphanumericCharacters) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=<>?";

        // Generar la contraseña aquí, como se hizo anteriormente
        return null;
    }

    public static class Car {
        private String id;
        private String marca;
        private String linea;
        private String modelo;
        // Agrega más campos según tus requerimientos

        public Car(String id, String marca, String linea, String modelo) {
            this.id = id;
            this.marca = marca;
            this.linea = linea;
            this.modelo = modelo;
            // Inicializa los demás campos según lo que necesites
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getLinea() {
            return linea;
        }

        public void setLinea(String linea) {
            this.linea = linea;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        // Implementa los getters y setters para los demás campos
    }
}

