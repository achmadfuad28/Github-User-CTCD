package comtest.ct.cd.achmadfuad.presentation.framework.owner

import comtest.ct.cd.achmadfuad.presentation.framework.base.BaseViewModel

interface ViewModelOwner<T : BaseViewModel> {
    val viewModel: T
}
