package fileStuff;

import java.io.*;

import utilidades.MyObjectOutputStream;
import utilidades.Utilidades;
import clases.Categoria;
import clases.Empleado;

public class FileProject5 {

	public static void main(String[] args) {
		File emp=new File("Empleados.dat");
		File categ=new File("Categorias.dat");
		int menu=menu();

		do {

			switch (menu) {

			case 1:
				add(emp,categ);
				break;

			case 2:
				addCateg(categ);
				break;

			case 3:
				modifyDeptEmpCode(emp,categ);
				break;

			case 4:

				break;

			case 5:

				break;

			case 6:
				System.out.println("Adios!");
			}
		} while (menu!=6);
	}

	public static int menu() {
		System.out.println("1. Alta de empleado");
		System.out.println("2. Alta de categoría");
		System.out.println("3. Modificación del departamento de un empleado a partir de su código de empleado");
		System.out.println("4. Listado de los departamentos con el número de empleados que hay en cada departamento");
		System.out.println("5. Listado ordenado por categoría");
		System.out.println("6. Salir");
		return Utilidades.leerInt("Elija una opcion:",1,6);
	}

	public static int generateCodeC(File categ) {
		return Utilidades.calculoFichero(categ)+1;
	}

	public static int generateCode(File emp) {
		return Utilidades.calculoFichero(emp)+100;
	}

	public static void add(File emp, File categ) {
		String name, surN, dni, dept;
		int codeC;
		boolean end=false, found=false;
		ObjectInputStream ois;
		ObjectOutputStream oos;
		MyObjectOutputStream moos;
		if (!categ.exists()) {
			System.out.println("No puede añadir un empleado sin tener añadidas categorias. Volviendo al menu...");
		} else {
			System.out.println("Introduce el nombre:");
			name=Utilidades.introducirCadena();
			System.out.println("Introduce el apellido:");
			surN=Utilidades.introducirCadena();
			System.out.println("Introduce el DNI:");
			dni=Utilidades.introducirCadena();

			System.out.println("El codigo del empleado es automaticamente generado.\n");

			System.out.println("Introduce el departamento:");
			dept=Utilidades.introducirCadena();
			do {
				System.out.println("Introduce el codigo de la categoria:");
				codeC=Utilidades.leerInt();
				try {
					ois=new ObjectInputStream(new FileInputStream(categ));
					try {
						Categoria c=(Categoria)ois.readObject();
						while (!end) {
							if (c.getCodeC()==codeC) {
								System.out.println("La categoria introducida es correcta.");
								found=true;
							}
						}
						ois.close();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (EOFException e) {
						end=true;
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (!found) {
					System.out.println("La categoria introducida es invalida. Introduzca una categora valida.");
				}
			} while (!found);
			Empleado eI=new Empleado(name, surN, dni, generateCode(emp), dept, codeC);

			if (emp.exists()) {
				try {
					moos=new MyObjectOutputStream(new FileOutputStream(emp,true));
					moos.writeObject(eI);
					System.out.println("El empleado ha sido añadido al fichero.");
					moos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					oos=new MyObjectOutputStream(new FileOutputStream(emp));
					oos.writeObject(eI);
					System.out.println("El fichero ha sido creado con el empleado.");
					oos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void addCateg(File categ) {
		String desc;
		double salary;
		ObjectOutputStream oos;
		MyObjectOutputStream moos;

		System.out.println("El codigo de la categoria es automaticamente generado.");
		do {
			System.out.println("Introduce la descripcion:");
			desc=Utilidades.introducirCadena();
			if (desc.isBlank()) {
				System.out.println("La descripcion no puede esta vacia.");
			}
		} while (desc.isBlank());

		do {
			System.out.println("Introduce el salario:");
			salary=Utilidades.leerDouble();
			if (salary<0) {
				System.out.println("El salario no puede ser negativo.");
			}
		} while (salary<0);
		Categoria cI=new Categoria(generateCodeC(categ),desc,salary);

		if (categ.exists()) {
			try {
				moos=new MyObjectOutputStream(new FileOutputStream(categ,true));
				moos.writeObject(cI);
				System.out.println("La categoria ha sido añadida al fichero.");
				moos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				oos=new MyObjectOutputStream(new FileOutputStream(categ));
				oos.writeObject(cI);
				System.out.println("El fichero ha sido creado con la categoria.");
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void modifyDeptEmpCode(File emp, File categ) {
		File empPH=new File("empleadoPH.dat");
		ObjectInputStream ois;
		ObjectOutputStream oos;
		int code;
		String newDept;
		boolean found=false, end=false;

		System.out.println("Introduce el codigo del empleado:");
		code=Utilidades.leerInt();

		try {
			ois=new ObjectInputStream(new FileInputStream(emp));
			oos=new ObjectOutputStream(new FileOutputStream(empPH));
			while (!end||!found) {
				try {
					Empleado e=(Empleado)ois.readObject();
					if (e.getCode()==code) {
						found=true;
						System.out.println("Introduce el nuevo departamento de la persona.");
						newDept=Utilidades.introducirCadena();
						e.setDept(newDept);
					}
					oos.writeObject(e);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (EOFException e) {
					end=true;
				}
			}
			oos.close();
			ois.close();
			if (!found) {
				System.out.println("No existen empleados con el codigo introducido.");
			} else {
				System.out.println("El departamento ha sido correctamente modificado.");
				if (emp.delete()) {
					empPH.renameTo(emp);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("FATAL ERROR");
		}
	}

	public static void listDeptDesc(File emp) {
		ObjectInputStream ois;
		boolean end=false;

		try {
			ois=new ObjectInputStream(new FileInputStream(emp));
			while (!end) {
				try {
					Empleado e=(Empleado)ois.readObject();

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (EOFException e) {
					end=true;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void listCateg(File emp, File categ) {

	}
}
