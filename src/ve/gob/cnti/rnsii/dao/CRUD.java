/* This file is part of RNSII.
 * 
 * RNSII is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RNSII is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with RNSII. If not, see <http://www.gnu.org/licenses/>.
 */
package ve.gob.cnti.rnsii.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ve.gob.cnti.rnsii.modelo.Correo;
import ve.gob.cnti.rnsii.modelo.EntradaSalida;
import ve.gob.cnti.rnsii.modelo.ServicioInformacion;
import ve.gob.cnti.rnsii.modelo.Telefono;
import ve.gob.cnti.rnsii.modelo.Url;
import ve.gob.cnti.rnsii.modelo.Visita;
import ve.gob.cnti.rnsii.util.ListaServiciosVisitados;
import ve.gob.cnti.rnsii.util.SectoresMasPublicados;
import ve.gob.cnti.rnsii.util.SubscriptionRequest;
import ve.gob.cnti.rnsii.util.SubscriptionResponse;
import ve.gob.cnti.rnsii.util.Tabs_incompletes;

/**
 * Definición de métodos para las operaciones en la base de datos.
 * 
 * @author Richard Ricciardelli
 * @author Joaquín Pereira
 * @see DAO
 * 
 */
public interface CRUD {

	/**
	 * Permite guardar un registro en la base de datos de cualquier modelo.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en el cual se realizará el
	 *            registro.
	 */
	public void create(Object model);

	/**
	 * Permite guardar un registro en la base de datos cuando el modelo es de
	 * una relación de unión M:N.
	 * 
	 * @author Richard Ricciardelli
	 * @author Joaquín Pereira
	 * @param model
	 *            Es la clase, modelo o tabla en el cual se realizará el
	 *            registro.
	 */
	public void createUnion(Object model);

	/**
	 * Permite eliminar un registro en la base de datos cuando el modelo es de
	 * una relación de unión M:N.
	 * 
	 * @author Richard Ricciardelli
	 * @author Joaquín Pereira
	 * @param model
	 *            Es la clase, modelo o tabla en el cual se realizará la
	 *            eliminación.
	 * 
	 * @param model2
	 *            Es la clase, modelo o tabla que es padre de la unión
	 * 
	 * 
	 * @param id
	 *            Es el identificador de la clase, modelo o tabla que es padre
	 *            de la unión
	 */
	public void deleteUnion(Object[] models, long id_u, long id);

	/**
	 * Permite obtener todos los registros activos del modelo dado.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return Una lista de objetos del modelo dado con sus atributos.
	 */
	public ArrayList<?> read(Object model);

	/**
	 * Permite obtener todos los registros activos del modelo resultante de la
	 * unión M:N de dos modelos dados.
	 * 
	 * @param unionModel
	 *            Modelo unión que contiene la relación resultante M:N de dos
	 *            tablas.
	 * @param model
	 *            Es la clase, modelo o tabla en el cual se realizará la
	 *            consulta como campo identificador.
	 * @param id
	 *            Es el identificador de la clase, modelo o tabla que sirve como
	 *            campo identificador.
	 * @return
	 */
	public ArrayList<?> readUnion(Object unionModel, Object model, long id);

	/**
	 * Permite obtener el registro activo especificado por el identificador del
	 * modelo dado.
	 * 
	 * @author Richard Ricciardelli
	 * @author Joaquín Pereira
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el identificador único del registro a consultar.
	 * @return El objeto modelo con sus atributos.
	 */
	public Object read(Object model, long id);

	/**
	 * Permite obtener una lista de registros del primer modelo dado dentro del
	 * arreglo. Funciona para relaciones 1:M donde el segundo modelo sirve de
	 * llave foránea dentro de la primer tabla.
	 * 
	 * @author Richard Ricciardelli
	 * @param models
	 *            Son las clases, modelos o tablas en las cuales se realizará la
	 *            consulta. Siendo la primera de éstas el tipo de objeto
	 *            resultante.
	 * @param id
	 *            Identificador de llave foránea dentro del primer modelo del
	 *            arreglo.
	 * @param type
	 *            Tipo de objeto que no será tomado en cuenta si tiene un valor
	 *            negativo.
	 * @return El listado de objetos resultantes.
	 */
	public ArrayList<?> read(Object models[], long id, int type);

	/**
	 * Permite obtener un valor de verdad sobre el nombre repetido en la base de
	 * datos de acuerdo a un criterio de relación 1:N donde el segundo modelo
	 * sirve de llave foránea.
	 * 
	 * @author Richard Ricciardelli
	 * @param models
	 *            Son las clases, modelos o tablas en las cuales se realizará la
	 *            consulta. Siendo la primera de éstas el tipo de objeto
	 *            resultante.
	 * @param id
	 *            Identificador de llave foránea dentro del primer modelo del
	 *            arreglo.
	 * @param name
	 *            Nombre del objeto.
	 * @return {@code true} si se encuentran coincidencias.
	 */
	public boolean read(Object[] models, long id, String name);

	/**
	 * Permite obtener una lista de objetos del modelo dado ordenado por nombre
	 * de forma ascendente o descendente dependiendo del caso.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param orderBy
	 *            Tipo de ordenamiento de la lista de objetos obtenida.
	 * @return
	 */
	public ArrayList<?> getSortedList(Object model, byte orderBy);

	/**
	 * Permite obtener el objeto correo de un usuario con el string dado.
	 * 
	 * @author Richard Ricciardelli
	 * @author Joaquín Pereira
	 * @param email
	 *            El string del correo que se quiere buscar.
	 * @return {@code correo} asociado a un usuario.
	 */
	public Correo getUserEmail(String email);

	/**
	 * Permite obtener el objeto correo de un identificador de modelo dado.
	 * 
	 * @author Richard Ricciardelli
	 * @author Joaquín Pereira
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del correo a consultar.
	 * @return {@code correo} asociado a un modelo.
	 */
	public Correo getEmail(Object model, long id);

	/**
	 * Permite modificar el registro especificado por id del modelo dado.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del registro a modificar.
	 */
	public void update(Object model, long id);

	/**
	 * Permite modificar el registro especificado por id del modelo resultante
	 * de una relación M:N.
	 * 
	 * @author Richard Ricciardelli
	 * @author Joaquín Pereira
	 * @param unionModel
	 *            Es el modelo resultante de una relación M:N.
	 * @param modelParent
	 *            Es la clase, modelo o tabla padre en la relación.
	 * @param modelChild
	 *            Es la clase, modelo o tabla hija en la relación.
	 * @param idParent
	 *            Es el identificador en el modelo padre.
	 * @param children
	 *            Lista de objetos hijos.
	 * @throws Exception
	 *             Arroja una excepción de no poder realizarse la invocación de
	 *             un método de alguna de las clases.
	 */
	public void updateUnion(Object unionModel, Object modelParent,
			Object modelChild, long idParent, List<?> children)
			throws Exception;

	/**
	 * Permite eliminar el registro especificado por id del modelo dado.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del registro a eliminar.
	 */
	public void delete(Object model, long id);

	/**
	 * Permite obtener el id lógico correspondiente al modelo dado para poder
	 * crear un nuevo registro.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return id Es el id lógico correspondiente.
	 */
	public long getNextId(Object model);

	/**
	 * Permite obtener el campo que será utilizado para obtener el id propio de
	 * cada modelo.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return El campo en el formato que se encuentra en la base de datos.
	 */
	public String getField(Object model);

	/**
	 * Permite obtener el sufijo para ser agregado al campo que será utilizado
	 * para la consulta del id de cada modelo.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return El sufijo del modelo en cuestión.
	 */
	public String getSuffix(Object model);

	/**
	 * Permite obtener los registros "padre" de un modelo determinado.
	 * 
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return Lista de padres activos en esa tabla.
	 */
	public ArrayList<?> getParents(Object model);

	/**
	 * Permite obtener los registros "hijo" de un modelo determinado.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @return Lista de hijos activos en esa tabla.
	 */
	public ArrayList<?> getChildren(Object model);

	/**
	 * Permite obtener la lista de los datos simples cargados en base de datos.
	 * 
	 * @author Richard Ricciardelli
	 * @return Lista de datos simples.
	 */
	public ArrayList<?> getSimple();

	/**
	 * Permite obtener la lista de los datos compuestos cargados en base de
	 * datos.
	 * 
	 * @author Richard Ricciardelli
	 * @return Lista de datos compuestos.
	 */
	public ArrayList<?> getComplex();

	/**
	 * Permite obtener el teléfono de una clase
	 * 
	 * @author Joaquín Pereira
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del teléfono a consultar.
	 * @return {@code telefono}
	 */
	public Telefono getPhone(Object model, long id);

	/**
	 * Permite obtener la URL de una clase dada.
	 * 
	 * @author Richard Ricciardelli
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @param id
	 *            Es el id del modelo a consultar.
	 * @return {@code url}
	 */
	public Url getUrl(Object model, long id);

	/**
	 * Permite actualizar una misma tupla sobre los datos faltantes. No genera
	 * trazas de auditoría.
	 * 
	 * @author Richard Ricciardelli
	 * @author Joaquín Pereira
	 * @param model
	 *            Es la clase, modelo o tabla en la cual se realizará la
	 *            consulta.
	 * @throws NoSuchMethodException
	 *             Cuando no existe el método.
	 * @throws InvocationTargetException
	 *             Cuando la invocación no fue satisfactoria.
	 * @throws IllegalAccessException
	 *             Cuando es ilegal acceder a un comportamiento del objeto.
	 * @throws SecurityException
	 *             Excepción de seguridad.
	 * @throws IllegalArgumentException
	 *             Cuando es ilegal el argumento pasado.
	 */
	public void update(Object model) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	/**
	 * Permite saber si existe un registro activo mediante la llave foránea con
	 * las relaciones de las dos entidades, pasadas como argumentos.
	 * 
	 * @author Richard Ricciardelli
	 * @param models
	 *            Son las dos entidades relacionadas, primero la entidad padre y
	 *            luego la entidad hija
	 * @param id
	 *            Clave foránea de la entidad hija
	 * @return <code>true</code> en caso de que exista un registro válido,
	 *         <code>false</code> en caso contrario
	 */
	public boolean read(Object[] models, long id);

	/**
	 * Permite saber si existe un registro activo mediante la llave foránea con
	 * las relaciones de las dos entidades, pasadas como argumentos.
	 * 
	 * @author Joaquín Pereira
	 * @param models
	 *            Son las dos entidades relacionadas, primero la entidad padre y
	 *            luego la entidad hija
	 * @param id
	 *            Clave foránea de la entidad hija
	 * @return Objeto con sus atributos establecidos.
	 * 
	 */
	public Object readf(Object[] models, long id);

	/**
	 * Permite saber si existe un registro activo mediante la llave foránea con
	 * las relaciones de las dos entidades, pasadas como argumentos.
	 * 
	 * @author Joaquín Pereira
	 * @param models
	 *            Son las dos entidades relacionadas, primero la entidad padre y
	 *            luego la entidad hija
	 * @param id
	 *            Clave foránea de la entidad hija
	 * @return retorna un objeto con sus atributos establecidos.
	 * 
	 */
	public Object getUrlRecoveryPass(Object model, String Url);

	/**
	 * Permite saber el número de servicios de información publicados en el
	 * sector solicitado.
	 * 
	 * @author Joaquín Pereira
	 * @param id
	 *            id del sector a consultar
	 * @return retorna el número de servicios publicados del sector
	 * 
	 */
	public long nSiSector(long id);

	/**
	 * Permite buscar servicios en el que el nombre de estos, se asemejen a la
	 * cadena pasada
	 * 
	 * @author Joaquín Pereira
	 * @param cadena
	 *            Es la cadena a consultar
	 * 
	 * @return Retorna una lista de servicios
	 */
	public ArrayList<ServicioInformacion> buscarServicio(String cadena,
			byte orderBy);

	/**
	 * Permite guardar una visita.
	 * 
	 * @author Richard Ricciardelli
	 * @author Joaquín Pereira
	 * @param visita
	 *            Visita a guardar.
	 */
	public void saveVisit(Visita visita);

	/**
	 * Permite obtener el número de visitas de un servicio de información de
	 * acuerdo a su identificador.
	 * 
	 * @author Richard Ricciardelli
	 * @param id
	 *            Identificador del servicio de información
	 * @return Cantidad de visitas del servicio de información correspondiente
	 *         al identificador.
	 */
	public long getVisits(long id);

	/**
	 * Permite verificar el acceso de un cliente en un límite de tiempo
	 * determinado para poder contarse como una visita única.
	 * 
	 * @author Richard Ricciardelli
	 * @param ip
	 *            Dirección IP del cliente que está realizando la visita
	 * @return {@code true} si es una visita nueva fuera del lapso establecido,
	 *         de lo contrario {@code false}
	 */
	public boolean verifyClientAccess(String ip, long id);

	/**
	 * Permite Consultar los 5 servicios de información más visitados.
	 * 
	 * @author Joaquín Pereira
	 * @return Retorna una lista con los servicios de información más visitados.
	 */
	public List<ListaServiciosVisitados> SImasVisitados();

	/**
	 * Permite consultar una cantidad n de sectores con más servicios de
	 * información implementados y publicados.
	 * 
	 * @author Joaquín Pereira
	 * @param n
	 *            Número para limitar la consulta, un valor menor o igual a cero
	 *            se considera como una consulta sin limitante.
	 * @return Retorna una lista con los n sectores con más servicios de
	 *         información.
	 */
	public List<SectoresMasPublicados> sectoresMasPublicados(int n);

	/**
	 * Permite consultar los servicios de información implementados y
	 * publicados.
	 * 
	 * @author Joaquín Pereira
	 * @param n
	 *            Numero para limitar la consulta, un valor menor o igual a cero
	 *            se considera como una consulta sin limitante.
	 * 
	 * @return Retorna una lista con los servicios de información implemantados
	 *         y públicados.
	 */
	public ArrayList<ServicioInformacion> getSIList(byte orderBy);

	/**
	 * Retorna una lista de los servicios de información implementados, activos
	 * y publicados en el sector dado.
	 * 
	 * @author Joaquín Pereira
	 * @param id_sector
	 *            Identificador del sector al cual pertenecen los servicios de
	 *            información.
	 * @return Lista de los servicios de información con los criterios dados.
	 */
	public ArrayList<ServicioInformacion> getServicioInformacionPorSectorList(
			long id_sector, byte orderBy);

	/**
	 * Permite buscar servicios en el que el nombre de estos se asemejen a la
	 * cadena pasada
	 * 
	 * @author Joaquín Pereira
	 * @param cadena
	 *            Es la cadena a consultar id_ente Sirve para discriminar los
	 *            servicio de información del propio ente
	 * 
	 * @return Retorna una lista de servicios
	 */
	public ArrayList<ServicioInformacion> buscarServicio2(String cadena,
			byte orderBy, long id_ente);

	/**
	 * Permite saber el número solicitudes de suscripción no leídos que posee el
	 * ente
	 * 
	 * @author Joaquín Pereira
	 * @param id
	 *            id del ente a consultar
	 * @return retorna el número solicitudes de suscripción no leidas
	 * 
	 */
	public long peticionesSuscripcion(long id);

	/**
	 * Permite saber el número solicitudes de suscripción pendientes que posee
	 * el ente
	 * 
	 * @author Joaquín Pereira
	 * @param id
	 *            id del ente a consultar
	 * @return retorna el número solicitudes de suscripción pendientes
	 * 
	 */
	public long peticionesSuscripcionPendientes(long id);

	/**
	 * Retorna una lista de las solicitudes de suscrición a los servicios de
	 * información publicados por el ente
	 * 
	 * @author Joaquín Pereira
	 * @param id_ente
	 *            Identificador del ente al cual pertenecen las solicitudes de
	 *            suscripción a los servicios de información publicados.
	 * @return Lista de las solicitudes de suscrición a los servicios
	 *         información.
	 */
	public ArrayList<SubscriptionRequest> getSolicitudesSuscripcionPendientes(
			long id_ente, byte orderBy);

	/**
	 * Permite saber el número de solicitudes de suscripción
	 * Aceptadas-Rechazadas por leer.
	 * 
	 * @author Joaquín Pereira
	 * @param id
	 *            id del ente a consultar
	 * @return retorna el número solicitudes de suscripción Aceptadas-Rechazadas
	 *         por leer
	 * 
	 */
	public long getNumeroSuscrionesAceptadasRechazadas(long id);

	/**
	 * Retorna una lista de las solicitudes de suscrición Aceptadas - Rechazadas
	 * 
	 * @author Joaquín Pereira
	 * @param id_ente
	 *            Identificador del ente al cual pertenecen las solicitudes de
	 *            suscripción aceptadas - rechazadas a buscar.
	 * @return Lista de las solicitudes de suscrición Aceptadas -Rechazadas.
	 */
	public ArrayList<SubscriptionResponse> getlistaSolicitudesAceptadasRechazadas(
			long id_ente, byte orderBy);

	/**
	 * Permite obtener las solicitudes de suscripción al servicio del proveedor
	 * dado por parte de un cliente.
	 * 
	 * @author Joaquín Pereira
	 * @param service
	 *            Servicio de información solicitado
	 * @param provider
	 *            Proveedor del servicio de información solicitado
	 * @param client
	 *            Cliente que solicita el servicio de información
	 * @return Un objeto {@link SubscriptionRequest} ó 0 si no hay
	 *         coincidencias.
	 */
	public long getId_solicitud_sucripcion(long service, long provider,
			long client);

	/**
	 * Permite verificar si una solicitud de suscripción existe actualmente o
	 * no.
	 * 
	 * @author Joaquín Pereira
	 * @param service
	 *            Servicio de información solicitado
	 * @param providerProveedor
	 *            del servicio de información solicitado
	 * @param client
	 *            Cliente que solicita el servicio de información
	 * @return <code>true</code> si la solicitud ya existe, de lo contrario
	 *         <code>false</code>
	 */
	public boolean verifySuscriptionRequest(long service, long provider,
			long client);

	/**
	 * Este método permite saber si un servicio de información está completo y
	 * listo para ser publicado. Teniendo en cuenta que el servicio debe estar
	 * en estado implementado, debe tener por lo menos una funcionalidad con por
	 * lo menos una salida.
	 * 
	 * @param servicio
	 *            Servicio de información a verificar.
	 * @return <code>true</code> si está completo, <code>false</code> si no lo
	 *         está.
	 */
	public boolean isComplete(ServicioInformacion servicio);

	/**
	 * Este método permite saber si una entrada o salida de tipo lista de datos
	 * simples tiene aunque sea un dato asociado. Es decir, si la entrada o
	 * salida es padre con por lo menos un hijo.
	 * 
	 * @param es
	 *            Entrada o Salida a evaluar.
	 * @return <code>true</code> si tiene hijos asociados, <code>false</code> si
	 *         no tiene.
	 */
	public boolean hasChildren(EntradaSalida es);

	/**
	 * Este método permite saber si un servicio de información está completo y
	 * listo para ser publicado, especificando mediante códigos de error en qué
	 * parte de la lógica el servicio no está completo.
	 * 
	 * @param servicio
	 *            Servicio de información a verificar.
	 * @return El código de error.
	 */
	public List<String> getIncompleteFields(ServicioInformacion servicio);

	/**
	 * Este método permite verificar si una entrada o una salida tiene el nombre
	 * duplicado en la base de datos.
	 * 
	 * @param idParent
	 *            El identificador de la funcionalidad a la que pertenecen.
	 * @param idChild
	 *            El identificador de la entrada o salida.
	 * @param type
	 *            Tipo <code>ENTRADA</code o <code>SALIDA</code>
	 * @param name
	 *            Nombre a evaluar que supuestamente estaría duplicado.
	 * @return <code>true</code> si el nombre está duplicado, <code>false</code>
	 *         si no está duplicado.
	 */
	public boolean entradaSalidaDuplicada(long idParent, long idChild,
			int type, String name);
	
	/**
	 * Este método permite saber si un servicio de información está completo,
	 * de no estarlo especifica que pestañas están incompletas	 
	 * 
	 * @param servicio
	 *            Servicio de información a verificar.
	 * @return Referencia a Pestañas incompletas.
	 */
	public List<Tabs_incompletes> getIncompleteFields2(ServicioInformacion servicio);
}
