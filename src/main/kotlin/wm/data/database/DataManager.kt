package wm.data.database
import wm.data.dao.CityDAO
import wm.data.dao.FeastDAO
import wm.data.dao.TownDAO
import wm.data.dao.UserDAO
import wm.data.storage.CommentsStorage

object DataManager {
    val userDAO = UserDAO()
    val cityDAO = CityDAO()
    val townDAO = TownDAO(cityDAO)
    val feastDAO = FeastDAO(cityDAO, townDAO)
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