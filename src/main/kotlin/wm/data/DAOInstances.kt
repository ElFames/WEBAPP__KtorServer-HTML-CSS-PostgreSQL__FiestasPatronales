package wm.data

class DAOInstances {
    val urlMaps = "https://www.google.com/maps/embed?pb="
    val defaultLocation = "!1m18!1m12!1m3!1d2990.34561674049!2d2.1841046152918757!3d41.453418679258114!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12a4bcfdad2100a9%3A0x3c4e7db0a61a83e0!2sInstitut%20Tecnol%C3%B2gic%20de%20Barcelona!5e0!3m2!1ses!2ses!4v1670121903091!5m2!1ses!2ses"
    val userDAO = UserDAO()
    val cityDAO = CityDAO()
    val townDAO = TownDAO(cityDAO)
    val feastDAO = FeastDAO(cityDAO,townDAO)
}