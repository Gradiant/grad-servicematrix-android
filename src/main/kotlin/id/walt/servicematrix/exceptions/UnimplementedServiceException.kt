package id.walt.servicematrix.exceptions

class UnimplementedServiceException(service: String?) :
    Exception("No implementation has been registered for service: $service")
