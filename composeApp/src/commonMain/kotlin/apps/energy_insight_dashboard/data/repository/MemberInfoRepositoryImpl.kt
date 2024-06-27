package apps.energy_insight_dashboard.data.repository

import apps.energy_insight_dashboard.domain.model.MemberInfo
import apps.energy_insight_dashboard.domain.repository.MemberInfoRepository
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.firoz
import composemultiplatformsamples.composeapp.generated.resources.jacquiline
import composemultiplatformsamples.composeapp.generated.resources.sennorita

class MemberInfoRepositoryImpl : MemberInfoRepository {
    override fun getMemberInfo(): List<MemberInfo> {
        return listOf(
            MemberInfo(
                name = "Jacquiline",
                access = "Full Access",
                profilePicRes = Res.drawable.jacquiline
            ),
            MemberInfo(
                name = "Sennorita",
                access = "Limited Access",
                profilePicRes = Res.drawable.sennorita
            ),
            MemberInfo(
                name = "Firoz",
                access = "Full Access",
                profilePicRes = Res.drawable.firoz
            ),
        )
    }
}