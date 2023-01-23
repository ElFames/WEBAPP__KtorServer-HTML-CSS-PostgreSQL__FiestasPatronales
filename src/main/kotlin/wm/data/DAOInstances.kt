package wm.data
import wm.storage.CommentsStorage

object DAOInstances {
    val userDAO = UserDAO()
    val cityDAO = CityDAO()
    val townDAO = TownDAO(cityDAO)
    val feastDAO = FeastDAO(cityDAO,townDAO)
    val commentstorage = CommentsStorage()

    init {
        commentstorage.loadComments()
        userDAO.newUser("admin","admin")

        // esto es para que hayan ciudades y pueblos de prueba ya que la base de datos no esta publicada
        cityDAO.defaultCitys.forEachIndexed { i, city ->
            cityDAO.addCity(city.first, city.second)
            townDAO.addTown(townDAO.defaultTowns[i], null, city.first)
        }
    }
}