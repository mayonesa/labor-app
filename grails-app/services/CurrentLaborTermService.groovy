class CurrentLaborTermService {

    boolean transactional = true

    def getLaborTerm() {
		CurrentLaborTerm.get(1)?.getLaborTerm()
    }
}
