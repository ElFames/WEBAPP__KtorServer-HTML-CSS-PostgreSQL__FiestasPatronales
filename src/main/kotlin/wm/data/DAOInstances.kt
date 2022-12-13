package wm.data
import wm.storage.CommentsStorage

class DAOInstances {
    val userDAO = UserDAO()
    val cityDAO = CityDAO()
    val townDAO = TownDAO(cityDAO)
    val feastDAO = FeastDAO(cityDAO,townDAO)
    val commentstorage = CommentsStorage()

    init {
        commentstorage.loadComments()
        userDAO.newUser("admin","admin")
    }
}