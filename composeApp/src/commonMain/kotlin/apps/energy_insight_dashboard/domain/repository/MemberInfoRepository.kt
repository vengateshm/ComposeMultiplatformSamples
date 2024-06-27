package apps.energy_insight_dashboard.domain.repository

import apps.energy_insight_dashboard.domain.model.MemberInfo

interface MemberInfoRepository {
    fun getMemberInfo(): List<MemberInfo>
}