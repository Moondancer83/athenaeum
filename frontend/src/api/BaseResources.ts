export interface ResourceEventTarget extends EventTarget {
  response: string;
}

export default class BaseResources {
  public static call(
    method: string,
    url: string,
    onLoadEnd: (e: ProgressEvent<EventTarget>) => void,
    onLoadStart?: (e: ProgressEvent<EventTarget>) => void,
    onProgress?: (e: ProgressEvent<EventTarget>) => void
  ): void {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url);

    if (onProgress) {
      xhr.onprogress = onProgress;
    }
    if (onLoadStart) {
      xhr.onloadstart = onLoadStart;
    }
    xhr.onloadend = onLoadEnd;

    xhr.send();
  }
}